using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Mind
{
    class MindRecord
    {
        private const string delimiter = ";";
        public DateTime time;
        public float iAttention;
        public float iMeditation;
        public float iRaw;
        public float iDelta;
        public float iTheta;
        public float iAlpha1;
        public float iAlpha2;
        public float iBeta1;
        public float iBeta2;
        public float iGamma1;
        public float iGamma2;


        public static string getCSVHeader()
        {
            return "DateTime" + delimiter + "Attention" + delimiter +
            "Meditation" + delimiter +
            "Raw" + delimiter +
            "Delta" + delimiter +
            "Theta" + delimiter +
            "Alpha1" + delimiter +
            "Alpha2" + delimiter +
            "Beta1" + delimiter +
            "Beta2" + delimiter +
            "Gamma1" + delimiter +
            "Gamma2";
        }
        public string getCSVRecord()
        {
            return  time.ToLocalTime() + delimiter +
                    iAttention + delimiter +
                    iMeditation + delimiter +
                    iRaw + delimiter +
                    iDelta + delimiter +
                    iTheta + delimiter +
                    iAlpha1 + delimiter +
                    iAlpha2 + delimiter +
                    iBeta1 + delimiter +
                    iBeta2 + delimiter +
                    iGamma1 + delimiter +
                    iGamma2 + delimiter;

        }

    }
}
