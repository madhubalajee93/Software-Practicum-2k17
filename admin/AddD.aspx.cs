using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;

public partial class AddD : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=103.21.58.192;Persist Security Info=True;User ID=SmartHealth;Password=!Qogf999");
    
    protected void Page_Load(object sender, EventArgs e)
    {
        SqlCommand cmd;
        string com = "select top 1 Id From Dise ORDER BY Id Desc;";
        con.Open();
        cmd = new SqlCommand(com, con);
        object count = cmd.ExecuteScalar();
        if (count != null)
        {
            int i = Convert.ToInt32(count);
            i++;
            TextBox3.Text = i.ToString();
        }
        else
        {
            TextBox3.Text = "101";
        }
        con.Close();
    }

    public string check()
    {
        if (TextBox1.Text == "")
        {
            return "Name";
        }
        else if (TextBox2.Text == "")
        {
            return "Symptoms";
        }
        else if (DropDownList1.Text == "--Select--")
        {
            return "Type";
        }
        else
        {
            return "OK";
        }
    }

    protected void Button1_Click(object sender, EventArgs e)
    {
        if (check() == "OK")
        {
            SqlConnection con = new SqlConnection();
            con.ConnectionString = @"Data Source=103.21.58.192;Persist Security Info=True;User ID=SmartHealth;Password=!Qogf999";
            con.Open();
            SqlCommand cmd = new SqlCommand("insert into Dise(Id,DName,Sym,Type,Flag) values (@DId,@Name,@Sym,'"+DropDownList1.Text+"','0');", con);
            cmd.Parameters.AddWithValue("@DId", TextBox3.Text);
            cmd.Parameters.AddWithValue("@Name", TextBox1.Text);
            cmd.Parameters.AddWithValue("@Sym", TextBox2.Text);
            cmd.ExecuteReader();
            con.Close();
            con.Open();

            Page.ClientScript.RegisterStartupScript(GetType(), "msgbox", "alert('Disease Added');", true);
            TextBox2.Text = "";
            TextBox3.Text = "";
        }
        else
        {
            Page.ClientScript.RegisterStartupScript(GetType(), "msgbox", "alert('Please Enter " + check() + "');", true);
        }
    }
}