using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

public partial class ViewDisease : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(@"Data Source=103.21.58.192;Persist Security Info=True;User ID=SmartHealth;Password=!Qogf999");

    protected void Page_Load(object sender, EventArgs e)
    {
        string s = "SELECT * FROM Dise";
        SqlDataAdapter da = new SqlDataAdapter(s, con);
        DataSet ds = new DataSet();
        da.Fill(ds);
        GridView1.DataSource = ds;
        GridView1.DataBind();
    }
}