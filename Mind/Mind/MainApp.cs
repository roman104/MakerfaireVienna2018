using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using libStreamSDK;
using System.IO.Ports;
using System.Windows.Forms.DataVisualization.Charting;

namespace Mind
{
    public partial class MainApp : Form
    {

        private bool active = true;
        public float signal = 0;

        private string sLogFile = "N/A";
        private string sDataLog = "N/A";

        private const string comPortName_prefix = "\\\\.\\";

        private int connectionID = -1;
        private int packetsRead = 0;
        private int iLiveFlag = 0;
        private int iMaxFlag = 100;

        private string comPort;

        private List<MindRecord> mindRecords;

        System.IO.StreamWriter file =
            new System.IO.StreamWriter(string.Format(@"data/" + "{0}{1:yyyy-MM-dd-h-mm-ss}.csv", "mind", DateTime.Now));

        private List<float> attention = new List<float>();
        private List<float> meditation = new List<float>();


        public MainApp()
        {
            InitializeComponent();

            lb_driverVersion.Text = "no driver installed";
            lb_driverVersion.ForeColor = Color.DarkRed;

            lb_connID.Text = "None";
            lb_connID.ForeColor = Color.Gray;

            string sInfixTS = DateTime.Now.Year + DateTime.Now.Month.ToString().PadLeft(2, '0') + DateTime.Now.Day.ToString().PadLeft(2, '0') + DateTime.Now.Hour.ToString().PadLeft(2, '0') + DateTime.Now.Minute.ToString().PadLeft(2, '0') + DateTime.Now.Second.ToString().PadLeft(2, '0');
            sLogFile = "streamLog_" + sInfixTS +  ".txt";
            sDataLog = "dataLog_" + sInfixTS + ".txt";

            lb_logFile.Text = sLogFile;
            lb_dataLogFile.Text = sDataLog;

            lb_streamLog.Text = "None";
            lb_dataLog.Text = "None";
            lb_connect.Text = "Not Connectced";
            lb_connect.ForeColor = Color.DarkRed;
            pb_working.Image = Properties.Resources.nosignal_v1;         
    
            this.sLogFile = "streamLog.txt";
            this.sDataLog = "dataLog.txt";

            lb_liveflag.Text = "";

            mindRecords = new List<MindRecord>();

            //COM PORTS
            string[] ports = SerialPort.GetPortNames();

            // Display each port name to the console.
            int i = 0;
            foreach (string port in ports)
            {
                cb_ports.Items.Add(port);
                if(port == "COM5")
                {
                    cb_ports.SelectedIndex = i;
                }
                i++;
            }

            this.comPort = cb_ports.Text;

            if(cb_ports.Items.Count > 0)
            {
                bt_connect.Enabled = true;
            }
            else
            {
                bt_connect.Enabled = false;
            }

            chb_readData.Enabled = false;

            _initDriver();
            
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //pb_working.Image = Properties.Resources.animatedCircle;
            comPort = comPortName_prefix + cb_ports.Text;
            bt_connect.Enabled = false;

            //FIX CURSOR

            //Cursor.Current = Cursors.WaitCursor;
            Task.Run(_connect);
           // Cursor.Current = Cursors.Default;
        }

        private async Task _connect()
        {
            int errCode = NativeThinkgear.TG_Connect(connectionID,
                         comPort,
                         NativeThinkgear.Baudrate.TG_BAUD_57600,
                         NativeThinkgear.SerialDataFormat.TG_STREAM_PACKETS);

            if (errCode < 0)
            {
                lb_connect.Invoke(new Action(() =>
                {
                    lb_connect.Text = "Not Connected";
                    lb_connect.ForeColor = Color.DarkRed;

                }));

                pb_working.Invoke(new Action(() => { pb_working.Image = Properties.Resources.nosignal_v1; }));
                bt_connect.Invoke(new Action(() => { bt_connect.Enabled = true; }));
            }
            else
            {
                lb_connect.Invoke(new Action(() =>
                {
                    lb_connect.Text = "Connected";
                    lb_connect.ForeColor = Color.DarkGreen;
                }));

                chb_readData.Invoke(new Action(() => { chb_readData.Enabled = true; }));
                pb_working.Invoke(new Action(() => { pb_working.Image = Properties.Resources.connecting1_v1; }));
            }

           
        }

        private void _initDriver()
        {
            float errCode = 0;

            NativeThinkgear thinkgear = new NativeThinkgear();

            /* Print driver version number */
            lb_driverVersion.Text = NativeThinkgear.TG_GetVersion().ToString();
            lb_driverVersion.ForeColor = Color.DarkGreen;

            /* Get a connection ID handle to ThinkGear */
            connectionID = NativeThinkgear.TG_GetNewConnectionId();
            lb_connID.Text = connectionID.ToString();


            if (connectionID < 0)
            {
                lb_driverVersion.ForeColor = Color.DarkRed;
                return;
            }
            else
            {
                lb_connID.ForeColor = Color.DarkGreen;
            }

            if (errCode < 0)
            {
                lb_streamLog.Text = errCode.ToString();
                lb_streamLog.ForeColor = Color.DarkRed;
                return;
            }
            else
            {
                lb_streamLog.ForeColor = Color.DarkGreen;
                lb_streamLog.Text = "OK";
            }

            /* Set/open data (ThinkGear values) log file for connection */
            if (errCode < 0)
            {
                lb_dataLog.Text = errCode.ToString();
                lb_streamLog.ForeColor = Color.DarkRed;
                return;
            }
            else
            {
                lb_dataLog.ForeColor = Color.DarkGreen;
                lb_dataLog.Text = "OK";
            }
        }



        private void MainApp_FormClosing(object sender, FormClosingEventArgs e)
        {
            file.Flush();
            file.Close();
            disconnect();
        }

        private async Task _readRawData()
        {
            int errCode = 0;
            file.WriteLine(MindRecord.getCSVHeader());

            iLiveFlag = 0;

            while (active)
            {
                /* Attempt to read a Packet of data from the connection */
                errCode = NativeThinkgear.TG_ReadPackets(connectionID, 1);

                /* If TG_ReadPackets() was able to read a complete Packet of data... */
                if (errCode == 1)
                {
                    if(iLiveFlag > iMaxFlag)
                    {
                        iMaxFlag = iLiveFlag;

                    }
                    iLiveFlag = 0;

                    /* Check if connectio quality is OK */
                    errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_POOR_SIGNAL);
                    if (errCode != 0)
                    {
                        signal = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_POOR_SIGNAL);
                        /*
                         ak sa data=0 spojenie je OK
                         */
                        /* Get and print out the updated attention value */
                        if (signal == 0)
                        {
                            pb_working.Invoke(new Action(() => { pb_working.Image = Properties.Resources.connected_v1; }));

                            MindRecord mindRecord = new MindRecord();
                            packetsRead++;

                            mindRecord.time = DateTime.Now;

                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_ATTENTION);
                            if (errCode != 0)
                            {
                                mindRecord.iAttention = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_ATTENTION);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_MEDITATION);
                            if (errCode != 0)
                            {
                                mindRecord.iMeditation = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_MEDITATION);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_THETA);
                            if (errCode != 0)
                            {
                                mindRecord.iTheta = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_THETA);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_ALPHA1);
                            if (errCode != 0)
                            {
                                mindRecord.iAlpha1 = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_ALPHA1);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_ALPHA2);
                            if (errCode != 0)
                            {
                                mindRecord.iAlpha2 = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_ALPHA2);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_BETA1);
                            if (errCode != 0)
                            {
                                mindRecord.iBeta1 = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_BETA1);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_BETA2);
                            if (errCode != 0)
                            {
                                mindRecord.iBeta2 = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_BETA2);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_GAMMA1);
                            if (errCode != 0)
                            {
                                mindRecord.iGamma1 = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_GAMMA1);
                            }
                            /**/
                            errCode = NativeThinkgear.TG_GetValueStatus(connectionID, NativeThinkgear.DataType.TG_DATA_GAMMA2);
                            if (errCode != 0)
                            {
                                mindRecord.iGamma2 = NativeThinkgear.TG_GetValue(connectionID, NativeThinkgear.DataType.TG_DATA_GAMMA2);
                            }

                            attention.Add(mindRecord.iAttention);
                            meditation.Add(mindRecord.iMeditation);
                           // createWaweforms();
                            //mindRecords.Add(mindRecord);
                            file.WriteLine(mindRecord.getCSVRecord());
                            file.Flush();
                            await Task.Delay(1000);

                        }
                        else
                        {
                            if(signal == 200)
                            {
                                pb_working.Invoke(new Action(() => { pb_working.Image = Properties.Resources.connecting1_v1; }));
                            }
                            else
                            {
                                if(signal < 100)
                                {
                                    pb_working.Invoke(new Action(() => { pb_working.Image = Properties.Resources.connecting2_v1; }));
                                }
                                else
                                {
                                    pb_working.Invoke(new Action(() => { pb_working.Image = Properties.Resources.connecting3_v1; }));
                                }
                            }
                            
                        }

                    }
                }
                else
                {
                    //NEžIJE
                    iLiveFlag++;
                    
                    if(iLiveFlag  > iMaxFlag * 10)
                    {
                        pb_working.Invoke(new Action(() => { pb_working.Image = Properties.Resources.nosignal_v1; }));
                        reconnect();
                        iLiveFlag = 0;
                        iMaxFlag = 1000;
                    }
                }

                lb_liveflag.Invoke(new Action(() => { lb_liveflag.Text = "MAX: " + iMaxFlag.ToString() + " ACTUAL: " + iLiveFlag.ToString() + ""; }));

                if (errCode < 0)
                {
                    //prázdny buffer

                }

            } /* end "Read 10 Packets of data from connection..." */
            //
        }


        private void disconnect()
        {
            if (connectionID >= 0)
            {
                // disconnect 
                NativeThinkgear.TG_Disconnect(connectionID);
                /* Clean up */
                NativeThinkgear.TG_FreeConnection(connectionID);
                connectionID = -1;
            }

        }
    

        private void reconnect()
        {
            int errCode = -2;

            while ((errCode == -2) && active)
            {
                //
                errCode = NativeThinkgear.TG_Connect(connectionID,
                        comPortName_prefix + comPort,
                        NativeThinkgear.Baudrate.TG_BAUD_57600,
                        NativeThinkgear.SerialDataFormat.TG_STREAM_PACKETS);

            }
        }

        private void chb_readData_CheckedChanged(object sender, EventArgs e)
        {
            if (chb_readData.Checked)
            {
                Task.Run(_readRawData);
            }
            else
            {
                active = false;
                //chb_readData.Checked = false;
            }
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            var app = new MainApp();
            app.Show();    
        }

        private void chart3_Click(object sender, EventArgs e)
        {
            
        }

        private bool createdGraph = false;
        private bool createdHistogram = false;
        private void createGraph_Click(object sender, EventArgs e)
        {
            createWaweforms();
            createHistogram();
        }

        private void createWaweforms()
        {
            if (!createdGraph)
            {
                createdGraph = true;

                chart1.Series.Add("Meditation");
                chart1.Series["Meditation"].ChartType = SeriesChartType.Line;

                chart1.Series.Add("Attention");
                chart1.Series["Attention"].ChartType = SeriesChartType.Line;

            }

            foreach (var series in chart1.Series)
            {
                series.Points.Clear();
            }

            var meditationCount = meditation.Count();
            var attentionCount = attention.Count();
            
            int value = 0;
            int reducedValue = 0;

            if (meditationCount > 60) reducedValue = meditationCount - 61;

            while (!(value > 60 || value == meditationCount))
            {
                chart1.Series["Meditation"].Points.AddY(meditation[reducedValue + value]);
                chart1.Series["Meditation"].ChartArea = "ChartArea1";

                value++;
            }

            value = 0;

            if (attentionCount > 60) reducedValue = attentionCount - 61;

            while (!(value > 60 || value == attentionCount))
            {
                chart1.Series["Attention"].Points.AddY(attention[reducedValue + value]);
                chart1.Series["Attention"].ChartArea = "ChartArea1";

                value++;
            }

            /* foreach (double value in meditation)
             {
                 chart1.Series["Meditation"].Points.AddY(value);
                 chart1.Series["Meditation"].ChartArea = "ChartArea1";
             }

             foreach (double value in attention)
             {
                 chart1.Series["Attention"].Points.AddY(value);
                 chart1.Series["Attention"].ChartArea = "ChartArea1";
             }*/
        }

        private void createHistogram()
        {
            if (!createdHistogram)
            {
                createdHistogram = true;

                chart2.Series.Add("HistogramM");
                chart2.Series["HistogramM"].ChartType = SeriesChartType.Bar;
            }

            int[] histogramMeditation = new int[10];

            for (int i = 0; i < 10; i++)
            {
                histogramMeditation[i] = 0;
            }

            foreach (var series in chart2.Series)
            {
                series.Points.Clear();
            }

            foreach (var value in meditation)
            {
                if (value < 10) histogramMeditation[0]++;
                else if (value < 20) histogramMeditation[1]++;
                else if (value < 30) histogramMeditation[2]++;
                else if (value < 40) histogramMeditation[3]++;
                else if (value < 50) histogramMeditation[4]++;
                else if (value < 60) histogramMeditation[5]++;
                else if (value < 70) histogramMeditation[6]++;
                else if (value < 80) histogramMeditation[7]++;
                else if (value < 90) histogramMeditation[8]++;
                else if (value < 100) histogramMeditation[9]++;
            }

            for (int i = 0; i < 10; i++)
            {
                chart2.Series["HistogramM"].Points.AddY(histogramMeditation[i]);
                chart2.Series["HistogramM"].ChartArea = "ChartArea1";
            }
        }

        private void chart2_Click(object sender, EventArgs e)
        {

        }
    }
}
