using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Data;

/// <summary>
/// Summary description for sym
/// </summary>
public class sym
{
    SqlConnection con = new SqlConnection(@"Data Source=103.21.58.192;Persist Security Info=True;User ID=SmartHealth;Password=!Qogf999");
    
	public sym()
	{
		//
		// TODO: Add constructor logic here
		//
	}

    public void getSym()
    {
        SqlDataAdapter da = new SqlDataAdapter("Select Sym from Dise where Flag = '1'", con);
        DataSet ds = new DataSet();
        da.Fill(ds);
        int row = ds.Tables[0].Rows.Count;
        string sys = "";
        SqlCommand cmd;
        int i = 0;
    Start:
        while (i < row)
        {
            sys = ds.Tables[0].Rows[i][0].ToString();
            string[] values = sys.Split(',');

            int val = values.Length;
            string d1 = "";
            for (int i2 = 0; i2 < val; i2++)
            {
                cmd = new SqlCommand("Select * from Final where Sym Like '%" + values[i2].ToLower() + "%'", con);
                con.Open();
                SqlDataReader dr = cmd.ExecuteReader();
                d1 = values[i2];
                if (dr.HasRows)
                {
                    con.Close();

                }
                else
                {
                    con.Close();
                    cmd = new SqlCommand("Insert into Keyword values ('" + values[i2] + "')", con);
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                    i++;
                    goto Start;
                }
            }
            i++;
        }
    }

}