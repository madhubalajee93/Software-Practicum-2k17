using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data.SqlClient;
using System.Data;

public partial class ViewDoc : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        SqlConnection con = new SqlConnection(@"Data Source=103.21.58.192;Persist Security Info=True;User ID=SmartHealth;Password=!Qogf999");

        SqlDataAdapter da = new SqlDataAdapter("Select DId,Name,Address,Mobile,Cate from Doctor", con);
        DataSet ds = new DataSet();
        da.Fill(ds);
        GridView1.DataSource = ds;
        GridView1.DataBind();
    }
}