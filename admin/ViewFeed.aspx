<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="ViewFeed.aspx.cs" Inherits="ViewFeed" %>


<asp:Content ID="body" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
<div>

    <br />
    <br />
    <asp:Label ID="Label1" runat="server" Font-Size="X-Large" Font-Underline="True" 
        ForeColor="#1818E4" Text="View Feedback"></asp:Label>
    <br />
    <br />
    <br />
    <asp:GridView ID="GridView1" runat="server" BackColor="White" 
        BorderColor="#CCCCCC" BorderWidth="1px" CellPadding="4" ForeColor="Black" 
        GridLines="Horizontal" BorderStyle="None" Width="80%">
        <FooterStyle BackColor="#CCCC99" ForeColor="Black" />
        <HeaderStyle BackColor="#1818E4" Font-Bold="True" ForeColor="White" />
        <PagerStyle BackColor="White" ForeColor="Black" 
            HorizontalAlign="Right" />
        <SelectedRowStyle BackColor="#CC3333" ForeColor="White" Font-Bold="True" />
        <SortedAscendingCellStyle BackColor="#F7F7F7" />
        <SortedAscendingHeaderStyle BackColor="#4B4B4B" />
        <SortedDescendingCellStyle BackColor="#E5E5E5" />
        <SortedDescendingHeaderStyle BackColor="#242121" />
    </asp:GridView>
    <br />

</div>
</asp:Content>
