using System;
using System.Collections.Generic;
using System.Text;

namespace CarregaImagem
{
    public class Log
    {
        private System.IO.StreamWriter writer;
        private static Log instance = null;

        private Log()
        {
            writer = new System.IO.StreamWriter("log.txt");
        }

        private static Log Instance
        {
            get
            {
                lock (typeof(Log))
                {
                    if (instance == null)
                        instance = new Log();
                    return instance;
                }
            }
        }

        private void InternalWriteLine(String str)
        {
            writer.WriteLine(str);
            writer.Flush();
        }

        public static void WriteLine(String str)
        {
            Instance.InternalWriteLine(str);
        }
    }
}
