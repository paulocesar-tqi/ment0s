using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace CarregaImagem
{
    public class Graph
    {
        private ImageWrapper image;
        private List<Point> lastPoints = new List<Point>();

        public Graph(ImageWrapper image)
        {
            this.image = image;
        }

        public List<Point> LastPoints
        {
            get { return lastPoints; }
        }


        public void FindAllPaths(List<Point> starters)
        {
            Log.WriteLine("FindAllPaths: " + DateTime.Now);
            
            lastPoints = starters;

            bool hasMoreLevels = true;
            while (hasMoreLevels)
            {
                List<Point> lstNewPoints = new List<Point>();
                foreach (Point pointA in lastPoints)
                {
                    List<Point> lstPoints = FindPoints(pointA);
                    foreach (Point p in lstPoints)
                    {
                        if (!lstNewPoints.Contains(p))
                            lstNewPoints.Add(p);
                    }

                }
                if (lstNewPoints.Count == 0)
                {
                    hasMoreLevels = false;
                }
                else
                {
                    lastPoints = lstNewPoints;
                }                
            }
            Log.WriteLine("FindAllPaths: " + DateTime.Now);
        }

        private List<Point> FindPoints(Point originalPoint)
        {
            List<Point> lstPoints = new List<Point>();
            List<Point> lstNextPoints = image.GetNextPoints(originalPoint);
            if (lstNextPoints.Count > 0)
            {
                foreach (Point point in lstNextPoints)
                {
                    if (image.CheckLine(originalPoint, point))
                    {
                        point.AddAncessor(originalPoint);
                        lstPoints.Add(point);
                    }
                }
            }
            return lstPoints;
        }
    }

    public class Path
    {
        private List<Point> path = new List<Point>();

        private bool used;

        public Path(Path path)
        {
            if (path != null)
                this.path.AddRange(path.path);
            Used = false;
        }

        public bool Used
        {
            get { return used; }
            set { used = value; }
        }

        public void Add(Point p)
        {
            path.Add(p);
            Used = false;
        }

        public Point LastPoint
        {
            get
            {
                return path[path.Count - 1];
            }
        }

        public List<Point> Points
        {
            get { return this.path; }
        }

        internal string Write()
        {
            StringBuilder sb = new StringBuilder();
            foreach (Point p in this.path)
            {
                sb.Append("(" + p.X + ", " + p.Y + ") ");
            }
            return sb.ToString();
        }
    }
}
