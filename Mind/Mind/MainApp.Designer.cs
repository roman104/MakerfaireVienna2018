namespace Mind
{
    partial class MainApp
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainApp));
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea2 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend2 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            this.statusImageList = new System.Windows.Forms.ImageList(this.components);
            this.bt_connect = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.lb_liveflag = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.lb_connect = new System.Windows.Forms.Label();
            this.cb_ports = new System.Windows.Forms.ComboBox();
            this.label7 = new System.Windows.Forms.Label();
            this.lb_dataLog = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.lb_dataLogFile = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.lb_streamLog = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.lb_logFile = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.lb_connID = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.lb_driverVersion = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.pb_working = new System.Windows.Forms.PictureBox();
            this.chb_readData = new System.Windows.Forms.CheckBox();
            this.newInstance = new System.Windows.Forms.Button();
            this.chart1 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.createGraph = new System.Windows.Forms.Button();
            this.chart2 = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pb_working)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart2)).BeginInit();
            this.SuspendLayout();
            // 
            // statusImageList
            // 
            this.statusImageList.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("statusImageList.ImageStream")));
            this.statusImageList.TransparentColor = System.Drawing.Color.Transparent;
            this.statusImageList.Images.SetKeyName(0, "connected_v1.bmp");
            this.statusImageList.Images.SetKeyName(1, "connecting1_v1.bmp");
            this.statusImageList.Images.SetKeyName(2, "connecting2_v1.bmp");
            this.statusImageList.Images.SetKeyName(3, "connecting3_v1.bmp");
            this.statusImageList.Images.SetKeyName(4, "nosignal_v1.bmp");
            // 
            // bt_connect
            // 
            this.bt_connect.Location = new System.Drawing.Point(29, 31);
            this.bt_connect.Margin = new System.Windows.Forms.Padding(4);
            this.bt_connect.Name = "bt_connect";
            this.bt_connect.Size = new System.Drawing.Size(113, 34);
            this.bt_connect.TabIndex = 0;
            this.bt_connect.Text = "Try to connect";
            this.bt_connect.UseVisualStyleBackColor = true;
            this.bt_connect.Click += new System.EventHandler(this.button1_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.lb_liveflag);
            this.groupBox1.Controls.Add(this.label9);
            this.groupBox1.Controls.Add(this.label8);
            this.groupBox1.Controls.Add(this.lb_connect);
            this.groupBox1.Controls.Add(this.cb_ports);
            this.groupBox1.Controls.Add(this.label7);
            this.groupBox1.Controls.Add(this.lb_dataLog);
            this.groupBox1.Controls.Add(this.label6);
            this.groupBox1.Controls.Add(this.lb_dataLogFile);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.lb_streamLog);
            this.groupBox1.Controls.Add(this.label4);
            this.groupBox1.Controls.Add(this.lb_logFile);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.lb_connID);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.lb_driverVersion);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(164, 31);
            this.groupBox1.Margin = new System.Windows.Forms.Padding(4);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Padding = new System.Windows.Forms.Padding(4);
            this.groupBox1.Size = new System.Drawing.Size(423, 306);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "NativeThinkgear Driver info";
            // 
            // lb_liveflag
            // 
            this.lb_liveflag.AutoSize = true;
            this.lb_liveflag.Location = new System.Drawing.Point(190, 270);
            this.lb_liveflag.Name = "lb_liveflag";
            this.lb_liveflag.Size = new System.Drawing.Size(41, 17);
            this.lb_liveflag.TabIndex = 17;
            this.lb_liveflag.Text = "#INIT";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Font = new System.Drawing.Font("Microsoft Sans Serif", 8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label9.Location = new System.Drawing.Point(94, 270);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(75, 17);
            this.label9.TabIndex = 16;
            this.label9.Text = "Live flag:";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Font = new System.Drawing.Font("Microsoft Sans Serif", 8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label8.Location = new System.Drawing.Point(126, 213);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(43, 17);
            this.label8.TabIndex = 4;
            this.label8.Text = "Port:";
            // 
            // lb_connect
            // 
            this.lb_connect.AutoSize = true;
            this.lb_connect.Location = new System.Drawing.Point(190, 242);
            this.lb_connect.Name = "lb_connect";
            this.lb_connect.Size = new System.Drawing.Size(41, 17);
            this.lb_connect.TabIndex = 15;
            this.lb_connect.Text = "#INIT";
            // 
            // cb_ports
            // 
            this.cb_ports.FormattingEnabled = true;
            this.cb_ports.Location = new System.Drawing.Point(194, 210);
            this.cb_ports.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.cb_ports.Name = "cb_ports";
            this.cb_ports.Size = new System.Drawing.Size(128, 24);
            this.cb_ports.TabIndex = 3;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 8F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label7.Location = new System.Drawing.Point(64, 242);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(103, 17);
            this.label7.TabIndex = 14;
            this.label7.Text = "TG_Connect:";
            // 
            // lb_dataLog
            // 
            this.lb_dataLog.AutoSize = true;
            this.lb_dataLog.Location = new System.Drawing.Point(190, 188);
            this.lb_dataLog.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lb_dataLog.Name = "lb_dataLog";
            this.lb_dataLog.Size = new System.Drawing.Size(41, 17);
            this.lb_dataLog.TabIndex = 13;
            this.lb_dataLog.Text = "#INIT";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label6.Location = new System.Drawing.Point(39, 188);
            this.label6.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(129, 17);
            this.label6.TabIndex = 12;
            this.label6.Text = "TG_SetDataLog:";
            // 
            // lb_dataLogFile
            // 
            this.lb_dataLogFile.AutoSize = true;
            this.lb_dataLogFile.Location = new System.Drawing.Point(190, 160);
            this.lb_dataLogFile.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lb_dataLogFile.Name = "lb_dataLogFile";
            this.lb_dataLogFile.Size = new System.Drawing.Size(41, 17);
            this.lb_dataLogFile.TabIndex = 11;
            this.lb_dataLogFile.Text = "#INIT";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label5.Location = new System.Drawing.Point(62, 160);
            this.label5.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(106, 17);
            this.label5.TabIndex = 10;
            this.label5.Text = "Data Log file:";
            // 
            // lb_streamLog
            // 
            this.lb_streamLog.AutoSize = true;
            this.lb_streamLog.Location = new System.Drawing.Point(190, 126);
            this.lb_streamLog.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lb_streamLog.Name = "lb_streamLog";
            this.lb_streamLog.Size = new System.Drawing.Size(41, 17);
            this.lb_streamLog.TabIndex = 9;
            this.lb_streamLog.Text = "#INIT";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label4.Location = new System.Drawing.Point(23, 126);
            this.label4.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(146, 17);
            this.label4.TabIndex = 8;
            this.label4.Text = "TG_SetStreamLog:";
            // 
            // lb_logFile
            // 
            this.lb_logFile.AutoSize = true;
            this.lb_logFile.Location = new System.Drawing.Point(190, 96);
            this.lb_logFile.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lb_logFile.Name = "lb_logFile";
            this.lb_logFile.Size = new System.Drawing.Size(41, 17);
            this.lb_logFile.TabIndex = 7;
            this.lb_logFile.Text = "#INIT";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label3.Location = new System.Drawing.Point(99, 96);
            this.label3.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(67, 17);
            this.label3.TabIndex = 6;
            this.label3.Text = "Log file:";
            // 
            // lb_connID
            // 
            this.lb_connID.AutoSize = true;
            this.lb_connID.Location = new System.Drawing.Point(190, 66);
            this.lb_connID.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lb_connID.Name = "lb_connID";
            this.lb_connID.Size = new System.Drawing.Size(41, 17);
            this.lb_connID.TabIndex = 5;
            this.lb_connID.Text = "#INIT";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label2.Location = new System.Drawing.Point(52, 66);
            this.label2.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(114, 17);
            this.label2.TabIndex = 4;
            this.label2.Text = "Connection ID:";
            // 
            // lb_driverVersion
            // 
            this.lb_driverVersion.AutoSize = true;
            this.lb_driverVersion.ForeColor = System.Drawing.Color.Black;
            this.lb_driverVersion.Location = new System.Drawing.Point(190, 34);
            this.lb_driverVersion.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lb_driverVersion.Name = "lb_driverVersion";
            this.lb_driverVersion.Size = new System.Drawing.Size(41, 17);
            this.lb_driverVersion.TabIndex = 3;
            this.lb_driverVersion.Text = "#INIT";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(238)));
            this.label1.Location = new System.Drawing.Point(99, 34);
            this.label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(73, 17);
            this.label1.TabIndex = 2;
            this.label1.Text = "Version: ";
            // 
            // pb_working
            // 
            this.pb_working.Location = new System.Drawing.Point(28, 131);
            this.pb_working.Margin = new System.Windows.Forms.Padding(4);
            this.pb_working.Name = "pb_working";
            this.pb_working.Size = new System.Drawing.Size(115, 97);
            this.pb_working.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pb_working.TabIndex = 2;
            this.pb_working.TabStop = false;
            // 
            // chb_readData
            // 
            this.chb_readData.Appearance = System.Windows.Forms.Appearance.Button;
            this.chb_readData.AutoSize = true;
            this.chb_readData.Location = new System.Drawing.Point(29, 80);
            this.chb_readData.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.chb_readData.Name = "chb_readData";
            this.chb_readData.Size = new System.Drawing.Size(114, 27);
            this.chb_readData.TabIndex = 4;
            this.chb_readData.Text = "Read  raw data";
            this.chb_readData.UseVisualStyleBackColor = true;
            this.chb_readData.CheckedChanged += new System.EventHandler(this.chb_readData_CheckedChanged);
            // 
            // newInstance
            // 
            this.newInstance.Location = new System.Drawing.Point(617, 31);
            this.newInstance.Name = "newInstance";
            this.newInstance.Size = new System.Drawing.Size(120, 34);
            this.newInstance.TabIndex = 8;
            this.newInstance.Text = "Add headband";
            this.newInstance.UseVisualStyleBackColor = true;
            this.newInstance.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // chart1
            // 
            chartArea1.Name = "ChartArea1";
            this.chart1.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.chart1.Legends.Add(legend1);
            this.chart1.Location = new System.Drawing.Point(40, 344);
            this.chart1.Name = "chart1";
            this.chart1.Size = new System.Drawing.Size(729, 257);
            this.chart1.TabIndex = 9;
            this.chart1.Text = "chart1";
            // 
            // createGraph
            // 
            this.createGraph.Location = new System.Drawing.Point(617, 80);
            this.createGraph.Name = "createGraph";
            this.createGraph.Size = new System.Drawing.Size(120, 27);
            this.createGraph.TabIndex = 10;
            this.createGraph.Text = "Create graph";
            this.createGraph.UseVisualStyleBackColor = true;
            this.createGraph.Click += new System.EventHandler(this.createGraph_Click);
            // 
            // chart2
            // 
            chartArea2.Name = "ChartArea1";
            this.chart2.ChartAreas.Add(chartArea2);
            legend2.Name = "Legend1";
            this.chart2.Legends.Add(legend2);
            this.chart2.Location = new System.Drawing.Point(40, 607);
            this.chart2.Name = "chart2";
            this.chart2.Size = new System.Drawing.Size(729, 254);
            this.chart2.TabIndex = 11;
            this.chart2.Text = "chart2";
            this.chart2.Click += new System.EventHandler(this.chart2_Click);
            // 
            // MainApp
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(781, 945);
            this.Controls.Add(this.chart2);
            this.Controls.Add(this.createGraph);
            this.Controls.Add(this.chart1);
            this.Controls.Add(this.newInstance);
            this.Controls.Add(this.chb_readData);
            this.Controls.Add(this.pb_working);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.bt_connect);
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "MainApp";
            this.Text = "MIND";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.MainApp_FormClosing);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pb_working)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chart2)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ImageList statusImageList;
        private System.Windows.Forms.Button bt_connect;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label lb_driverVersion;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lb_connID;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label lb_logFile;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label lb_streamLog;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label lb_dataLogFile;
        private System.Windows.Forms.PictureBox pb_working;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label lb_dataLog;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label lb_connect;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.ComboBox cb_ports;
        private System.Windows.Forms.CheckBox chb_readData;
        private System.Windows.Forms.Label lb_liveflag;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Button newInstance;
        private System.Windows.Forms.DataVisualization.Charting.Chart chart1;
        private System.Windows.Forms.Button createGraph;
        private System.Windows.Forms.DataVisualization.Charting.Chart chart2;
    }
}

