using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Net;
using System.IO;
using System.Net.Sockets;
using System.Threading;

namespace WinServerDDE
{
    public partial class Form1 : Form
    {
        delegate void SetTextCallback(string text);
        private static readonly Encoding DefaultEncoding = Encoding.ASCII;
        private static readonly byte[] LineTerminator = new byte[] { 13, 10 };
        private int step = 0;
        private const string socketHost = "189.90.8.164";
        private const string socketUrl = "/Adv.php?Log=0";
        private const string url = "htp://189.90.8.164/Adv.php?Sessao={0}&Cmd={1}";
        private string sessao = null;

        public Form1()
        {
            InitializeComponent();
        }

        private void SendAck(Object sender, EventArgs eArgs)
        {
            if (step == 2)
            {
                try
                {
                    string urlAck = string.Format(url, sessao, "ACK;");
                    HttpWebRequest http = (HttpWebRequest)HttpWebRequest.Create(urlAck);
                    http.KeepAlive = false;
                    http.Timeout = 10 * 1000;
                    HttpWebResponse response = (HttpWebResponse)http.GetResponse();
                    response.Close();
                }
                catch (Exception ex)
                {
                    outBox.AppendText(ex.Message);
                }
            }

        }

        private void SendLogin()
        {
            if (step == 1)
            {
                try
                {
                    string urlLogin = string.Format(url, sessao, "login;BRADESCO;;ptBR;3.20%20090601;0;;1;0;28080004760;");
                    HttpWebRequest http = (HttpWebRequest)HttpWebRequest.Create(urlLogin);
                    http.KeepAlive = false;
                    http.Timeout = 10 * 1000;
                    HttpWebResponse response = (HttpWebResponse)http.GetResponse();
                    response.Close();

                    step = 2;
                }
                catch (Exception ex)
                {
                    outBox.AppendText(ex.Message);
                }

            }

        }


        static void SendRequest(Stream stream, IEnumerable<string> request)
        {
            foreach (string r in request)
            {
                byte[] data = DefaultEncoding.GetBytes(r);
                stream.Write(data, 0, data.Length);
                stream.Write(LineTerminator, 0, 2);
            }
            stream.Write(LineTerminator, 0, 2);
            // Eat response
            string response = ReadLine(stream);
            
        }

        static string ReadLine(Stream stream)
        {
            List<byte> lineBuffer = new List<byte>();
            while (true)
            {
                int b = stream.ReadByte();
                if (b == -1 || b == 10 || b == 13)
                {
                    break;
                }
                if (b != 13)
                {
                    lineBuffer.Add((byte)b);
                }
            }
            if (lineBuffer.Count == 0)
                return null;
            return DefaultEncoding.GetString(lineBuffer.ToArray());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Thread threadObj = new Thread(CallSocket);
            threadObj.Start();
       }

        private void CallSocket()
        {
            try
            {

                IPHostEntry ipAddress = Dns.GetHostEntry(socketHost);
                IPEndPoint ip = new IPEndPoint(ipAddress.AddressList[0], 80);
                using (Socket socket = new Socket(ip.AddressFamily, SocketType.Stream, ProtocolType.Tcp))
                {
                    socket.Connect(ip);
                    using (NetworkStream n = new NetworkStream(socket))
                    {

                        //SendRequest(n, new string[] { "GET " + url + " HTTP/1.1", "Host: " + host, "Connection: Close", "Accept-Encoding: gzip" });
                        SendRequest(n, new string[] { "GET " + socketUrl + " HTTP/1.1", "Host: " + socketHost, "Accept-Encoding: gzip" });

                        while (true)
                        {
                            string line = ReadLine(n);

                            if (line != null)
                            {
                                SetTextCallback d = new SetTextCallback(SetText);
                                this.Invoke(d, new object[] { line });
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                SetTextCallback d = new SetTextCallback(SetText);
                this.Invoke(d, new object[] { ex.Message });
            }
        }
        
        private void SetText(string line)
        {
            if (step == 0)
            {
                if (line.IndexOf("CONECTADO") > 0)
                {
                    string[] dados = line.Split(';');
                    if (dados.Length > 5)
                    {
                        sessao = dados[5];
                        step = 1;
                    }
                }
            }
            
            outBox.AppendText(line + "\n");
        }

    }

}