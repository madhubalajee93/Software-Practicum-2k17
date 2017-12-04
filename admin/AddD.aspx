<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" AutoEventWireup="true" CodeFile="AddD.aspx.cs" Inherits="AddD" %>

<asp:Content ID="Body" runat="server" ContentPlaceHolderID="ContentPlaceHolder1">
    <div>

    <br />
    <br />
    <asp:Label ID="Label1" runat="server" Font-Size="X-Large" Font-Underline="True" 
        ForeColor="#1818E4" Text="Add Disease"></asp:Label>
&nbsp;
    <br />
    <br />
    <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Label ID="Label4" runat="server" Text="Disease ID  :-  "></asp:Label>
&nbsp;<asp:TextBox ID="TextBox3" runat="server" ReadOnly="True"></asp:TextBox>
    <br />
    <br />
    <asp:Label ID="Label2" runat="server" Text="Name of Disease  :-"></asp:Label>
&nbsp;
    <asp:TextBox ID="TextBox1" runat="server"></asp:TextBox>
    <br />
    <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <asp:Label ID="Label3" runat="server" Text="Symptoms of Disease  :-"></asp:Label>
&nbsp;
    <asp:TextBox ID="TextBox2" runat="server" Height="56px" TextMode="MultiLine" 
        Width="268px"></asp:TextBox>
    <asp:Label ID="Label5" runat="server" Font-Size="Small" ForeColor="Red" 
        Text="Septated By &quot;,&quot;"></asp:Label>
&nbsp;&nbsp;&nbsp;&nbsp;
    <br />
    <br />
    <asp:Label ID="Label6" runat="server" Text="Type of Disease  :-"></asp:Label>
&nbsp;
    <asp:DropDownList ID="DropDownList1" runat="server" Height="20px" Width="114px">
        <asp:ListItem>--Select--</asp:ListItem>
        <asp:ListItem>Heart</asp:ListItem>
        <asp:ListItem>Brain</asp:ListItem>
        <asp:ListItem>Physio</asp:ListItem>
        <asp:ListItem>Bone</asp:ListItem>
        <asp:ListItem>Infectious</asp:ListItem>
        <asp:ListItem>STD</asp:ListItem>
    </asp:DropDownList>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br />
    <br />
    <asp:Button ID="Button1" runat="server" onclick="Button1_Click" Text="Submit" />
    <br />
    <br />

</div>
</asp:Content>