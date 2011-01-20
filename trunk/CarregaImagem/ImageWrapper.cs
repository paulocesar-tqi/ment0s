using System;
using System.Collections.Generic;
using System.Text;
using System.Drawing;

namespace CarregaImagem
{
    public class ImageWrapper
    {
        private const int X_INCREMENTER = 15;

        private Bitmap imagem;
        private int lastX;
        private List<Point> lstFirstPoints;
        private List<Point> lstLastPoints;
        
        private Dictionary<int, List<Point>> memoryYPoints = new Dictionary<int, List<Point>>();
        private Dictionary<string, Point> memoryPoint = new Dictionary<string, Point>();

        public ImageWrapper(Bitmap bmp)
        {
            imagem = bmp;
            lstFirstPoints = GetFirstPoints();
            lstLastPoints = GetLastPoints();
        }

        public List<Point> FirstPoints
        {
            get { return this.lstFirstPoints; }
        }

        public Point GetPoint(int x, int y)
        {
            lock (memoryPoint)
            {
                string key = x + "|" + y;
                if (!memoryPoint.ContainsKey(key))
                {
                    memoryPoint[key] = new Point(x, y);
                }
                return memoryPoint[key];
            }
        }

        private List<Point> GetFirstPoints()
        {
            List<Point> lstPoints = new List<Point>();
            bool keepLoop = true;
            for (int x = 0; x < imagem.Width && keepLoop; x++)
            {
                for (int y = 0; y < imagem.Height; y++)
                {
                    if (IsGoodPixel(x, y))
                    {
                        lstPoints.Add(GetPoint(x, y));
                    }
                }
                if (lstPoints.Count < 3)
                {
                    keepLoop = true;
                }
                else
                {
                    keepLoop = false;
                }
            }
            return lstPoints;
        }

        private List<Point> GetLastPoints()
        {
            List<Point> lstPoints = new List<Point>();
            bool keepLoop = true;
            for (int x = imagem.Width-1; x > 0 && keepLoop; x--)
            {
                for (int y = 0; y < imagem.Height; y++)
                {
                    if (IsGoodPixel(x, y))
                    {
                        lstPoints.Add(GetPoint(x,y));                        
                    }
                }
                if (lstPoints.Count < 3)
                {
                    keepLoop = true;
                }
                else
                {
                    lastX = x;
                    keepLoop = false;
                }
            }
            return lstPoints;
        }

        public List<Point> GetNextPoints(Point point)
        {
            List<Point> lstPoints = new List<Point>();
            
            if (lstLastPoints.Contains(point))
                return lstPoints;

            int x = point.X + X_INCREMENTER;
            if (x > lastX)
                return lstLastPoints;

            if (memoryYPoints.ContainsKey(x))
            {
                return memoryYPoints[x];
            }

            for (int y = 0; y < imagem.Height; y++)
            {
                if (IsGoodPixel(x, y))
                {
                    lstPoints.Add(GetPoint(x,y));
                }

            }
            memoryYPoints[x] = lstPoints;
            return lstPoints;

        }
        public bool CheckLine(Point start, Point end)
        {
            Point a = new Point(start);
            Point b = new Point(end);

            int dy = b.Y - a.Y;
            int dx = b.X - a.X;
            float t = (float)0.5;                      // offset for rounding

            if (Math.Abs(dx) > Math.Abs(dy))
            {          // slope < 1
                float m = (float)dy / (float)dx;      // compute s
                t += a.Y;
                dx = (dx < 0) ? -1 : 1;
                m *= dx;
                while (a.X != b.X)
                {
                    a.X += dx;                           // step to next x value
                    t += m;                             // add slope to y value

                    int rounded = (int)t;
                    if (!IsGoodPixel(a.X, rounded))
                    {
                        return false;
                        /*rounded = (int)Math.Ceiling(t);
                        if (!IsGoodPixel(a.X, rounded))
                        {
                            rounded = (int)t-1;
                            if (!IsGoodPixel(a.X, rounded))
                            return false;
                        }
                         */
                    }

                }

            }
            else
            {
                // slope >= 1
                float m = (float)dx / (float)dy;      // compute slope
                t += a.X;
                dy = (dy < 0) ? -1 : 1;
                m *= dy;

                while (a.Y != b.Y)
                {
                    a.Y += dy;                           // step to next y value
                    t += m;                             // add slope to x value
                    if (!IsGoodPixel((int)t, a.Y))
                        return false;
                }
            }
            return true;
        }

        public void ClearImage()
        {
            Log.WriteLine("Clear Image: " + DateTime.Now);
            for (int x = 0; x < imagem.Width; x++)
            {
                for (int y = 0; y < imagem.Height; y++)
                {
                    if (IsGoodPixel(x,y) && IsAlonePixel(x, y))
                    {
                        imagem.SetPixel(x, y, Color.White);
                    }
                }
            }
            Log.WriteLine("Clear Image: " + DateTime.Now);
        }

        private bool IsGoodPixel(int x, int y)
        {
            if (x < 0 || x >= imagem.Width) return false;
            if (y < 0 || y >= imagem.Height) return false;

            Color col1 = imagem.GetPixel(x, y);
            return (col1.ToArgb() == Color.Black.ToArgb());
        }

        private bool IsAlonePixel(int x, int y)
        {
            int thresold = 1;
            int count = 0;
            count += IsGoodPixel(x + 1, y) ? 1 : 0;
            count += IsGoodPixel(x, y + 1) ? 1 : 0;
            count += IsGoodPixel(x - 1, y) ? 1 : 0;
            count += IsGoodPixel(x, y - 1) ? 1 : 0;
            
            count += IsGoodPixel(x - 1, y - 1) ? 1 : 0;
            count += IsGoodPixel(x - 1, y + 1) ? 1 : 0;
            count += IsGoodPixel(x + 1, y + 1) ? 1 : 0;
            count += IsGoodPixel(x + 1, y - 1) ? 1 : 0;

            return (count <= thresold);
        }

        internal void Paint(List<Point> lastPoints)
        {
            Log.WriteLine("Paint Image: " + DateTime.Now);
            using (Graphics g = Graphics.FromImage(imagem))
            {
                while (lastPoints.Count > 0)
                {
                    List<Point> lstPoints = new List<Point>();
                    foreach (Point point in lastPoints)
                    {
                        List<Point> lstAncessors = point.Ancessors;
                        CheckAncessors(lstAncessors);
                        foreach (Point ancessor in lstAncessors)
                        {
                            Point p = ancessor;
                            Point p2 = point;
                            g.DrawLine(new Pen(Color.Red, 1), new PointF(p.X, p.Y), new PointF(p2.X, p2.Y));
                            if (!lstPoints.Contains(ancessor))
                                lstPoints.Add(GetPoint(ancessor.X, ancessor.Y));
                        }
                    }
                    lastPoints = lstPoints;
                }
            }
            Log.WriteLine("Paint Image: " + DateTime.Now);
        }

        private void CheckAncessors(List<Point> lstAncessors)
        {
            List<Point> lstConnections = new List<Point>();
            foreach (Point p in lstAncessors)
            {
                Point newPoint = new Point(p);
                lstConnections.Add(newPoint);
            }

            foreach (Point p in lstConnections)
            {
                foreach (Point p2 in lstConnections)
                {
                    if (p.Y == p2.Y + 1 || p.Y == p2.Y - 1)
                    {
                        p.AddAncessor(p2);
                        p2.AddAncessor(p);
                    }
                }
            }

            bool existsConnection = true;
            while (existsConnection)
            {
                int count = 0;
                foreach (Point p in lstConnections)
                {
                    foreach (Point p2 in lstConnections)
                    {
                        if (!p.Equals(p2))
                        {
                            if (p.Ancessors.Contains(p2))
                            {                                
                                foreach (Point a in p2.Ancessors)
                                {
                                    if (!p.Ancessors.Contains(a) && !p.Equals(a))
                                    {
                                        p.AddAncessor(a);
                                        a.AddAncessor(p);
                                        count++;
                                    }
                                }
                            }
                        }

                    }
                }
                existsConnection = count > 0;
            }

            int maxConnections = 0;
            foreach (Point p in lstConnections)
            {
                int qtd = p.Ancessors.Count;
                if (qtd > maxConnections) maxConnections = qtd;
            }

            lstAncessors.Clear();

            foreach (Point p in lstConnections)
            {
                int qtd = p.Ancessors.Count;
                if (qtd == maxConnections)
                    lstAncessors.Add(p);
            }
        }

        internal void Repopulate()
        {
            Log.WriteLine("Repopulate: " + DateTime.Now);
            using (Graphics g = Graphics.FromImage(imagem))
            {
                for (int x = 0; x < imagem.Width; x++)
                {
                    for (int y = 0; y < imagem.Height; y++)
                    {
                        Color c = imagem.GetPixel(x, y);
                        int redY = y;

                        if (c.ToArgb() == Color.Red.ToArgb() && IsGoodPixel(x, y-1))
                        {
                            while (c.ToArgb() == Color.Red.ToArgb() && y < imagem.Height-1)
                            {
                                y++;
                                c = imagem.GetPixel(x, y);
                            }
                            
                            if (IsGoodPixel(x, y))
                            {
                                for (int i = redY; i < y; i++)
                                {
                                    imagem.SetPixel(x, i, Color.Black);
                                }
                                break;
                            }

                        }
                    }
                }
                for (int x = 0; x < imagem.Width; x++)
                {
                    for (int y = 0; y < imagem.Height; y++)
                    {
                        Color c = imagem.GetPixel(x, y);
                        if (c.ToArgb() == Color.Red.ToArgb())
                        {
                            imagem.SetPixel(x, y, Color.White);
                        }
                    }
                }
            }
            Log.WriteLine("Repopulate: " + DateTime.Now);
        }

    }
}
