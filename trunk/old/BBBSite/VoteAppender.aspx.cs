using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.IO;

public partial class VoteAppender : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        String strVotos = Request.QueryString["qtdVotos"];
        String ip = Request.UserHostAddress;
        String poolId = Request.QueryString["poolId"];

        if (String.IsNullOrEmpty(strVotos) || String.IsNullOrEmpty(poolId))
            return;

        int qtdVotos = 0;
        if (Int32.TryParse(strVotos, out qtdVotos))
            SaveFile(qtdVotos, ip, poolId);
    }

    private void SaveFile(int qtdVotos, String ip, String poolId)
    {
        String line = DateTime.Now.ToString("dd/MM/yyyy HH:mm:ss.S") + ";" + poolId + ";" + ip + ";" + qtdVotos + Environment.NewLine;
        lock (typeof(File))
        {
            File.AppendAllText(Server.MapPath("votos.txt"), line);
        }
        
    }
}
