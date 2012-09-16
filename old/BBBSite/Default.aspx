<%@ Page Language="C#" AutoEventWireup="true"  CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Label ID="lblCookie" runat="server" Text="Cookie" />
        <asp:Label ID="lblCookieValue" runat="server" />
        <br />
        <br />
        <asp:Label ID="lblUrlReferrer" runat="server" Text="Url Referrer" />
        <br />
        <asp:Label ID="lblUrlReferrerValue" runat="server" /> <br />
        <br />
        <br />
        <asp:Label ID="lblHeader" runat="server" Text="Headers" />
        <asp:Label ID="lblHeaderValue" runat="server" /> <br />
        <br />
        <br />
        <asp:Label ID="lblData" runat="server" Text="Form" />
        <asp:Label ID="lblDataValue" runat="server" /> <br />
        <br />
        <br />
        <asp:Label ID="lblQueryString" runat="server" Text="QueryString" />
        <asp:Label ID="lblQueryStringValue" runat="server" /> <br />
        <br />
        <br />
        <asp:Label ID="lblParams" runat="server" Text="Params" />
        <asp:Label ID="lblParamsValue" runat="server" /> <br />
        <br />
        <asp:TextBox ID="txtTeste" runat="server" />
        <asp:Button ID="btnTeste" runat="server" />
    </div>
    </form>
</body>
</html>
