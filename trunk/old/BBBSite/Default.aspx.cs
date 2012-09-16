using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Collections;
using System.Collections.Specialized;

public partial class _Default : System.Web.UI.Page 
{
    protected void Page_Load(object sender, EventArgs e)
    {
        lblCookieValue.Text = getValue(Request.Cookies);

        if (Request.UrlReferrer != null)
            lblUrlReferrerValue.Text = Request.UrlReferrer.AbsoluteUri;

        lblHeaderValue.Text = getValue(Request.Headers);

        lblDataValue.Text = getValue(Request.Form);

        lblParamsValue.Text = getValue(Request.Params);

        lblQueryStringValue.Text = getValue(Request.QueryString);
    }

    private string getValue(NameObjectCollectionBase c)
    {
        string value = "";        
        foreach (string key in c.Keys)
        {
            value += ("<br>" + key + "=");
            if (c is HttpCookieCollection)
            {
                value += ((HttpCookieCollection)c)[key];
            }
            else
            {
                value += ((NameValueCollection)c)[key];
            }
            
        }
        return value;
    }
    
}
