using System;
using System.Collections.Generic;
using System.Text;

namespace CarregaImagem
{
    public class Graph
    {
        private ImageWrapper image;
        private Queue<Path> lstPaths = new Queue<Path>();

        public Graph(ImageWrapper image)
        {
            this.image = image;
        }

        public IEnumerable<Path> Paths
        {
            get { return lstPaths; }
        }


        public void FindAllPaths(List<Point> starters)
        {
            foreach (Point start in starters)
            {
                Path startPath = new Path(null);
                startPath.Add(start);
                lstPaths.Enqueue(startPath);                
            }

            bool hasNotUsed = true;
            while (hasNotUsed)
            {
                Path p = this.lstPaths.Dequeue();
                if (!p.Used)
                {
                    p.Used = true;
                    List<Path> lstNewPaths = FindPaths(p);
                    if (lstNewPaths.Count > 0)
                    {
                        foreach (Path newPath in lstNewPaths)
                        {
                            this.lstPaths.Enqueue(newPath);
                        }
                    }
                }
                else
                {
                    this.lstPaths.Enqueue(p);
                    hasNotUsed = false;
                }
                
            }
        }

        private List<Path> FindPaths(Path originalPath)
        {
            List<Path> lstPaths = new List<Path>();
            Point lastPoint = originalPath.LastPoint;
            List<Point> lstPoints = image.GetNextPoints(lastPoint);
            if (lstPoints.Count > 0)
            {
                foreach (Point point in lstPoints)
                {
                    if (image.CheckLine(lastPoint, point))
                    {
                        Path newPath = new Path(originalPath);
                        newPath.Add(point);
                        lstPaths.Add(newPath);
                    }
                }
            }
            else
            {
                lstPaths.Add(originalPath);
            }
            return lstPaths;
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
