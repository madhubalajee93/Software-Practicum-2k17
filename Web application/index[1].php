<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title> Health Care</title>
        
        <script src="practicum.js" type="text/javascript"></script>
        <link href="practicum.css" rel="stylesheet" type="text/css"/>
    <a href="practicum.php"></a>
    </head>
    <body>
        <link href="practicum.css" rel="stylesheet" type="text/css"/>
        
    <center><h1>Health Care</h1></center>

    <form method="POST" action="/action_page.php">
  <div class="imgcontainer">
      <center><img src="image/image 2.jpg" alt="" style="width:800px;height:500px;"/></center>
  </div>
    <center><h2>Sign Up Form</h2></center>
  <form action="#" name="User Registration" onSubmit="return(validate());">

<table cellpadding="2" width="20%" bgcolor="99FFFF" align="center"

  cellspacing="2">


<tr>

  <td colspan=2>

  <center><font size=4><b>User Registration Form</b></font></center>

  </td>

  </tr>


<tr>

  <td>First Name</td>

  <td><input type=text name=textnames id="textname" size="30"></td>

  </tr>


<tr>

  <td>Last Name</td>

  <td><input type="text" name="fathername" id="fathername"

  size="30"></td>

  </tr>
  
  <tr>

  <td>Email Id</td>

  <td><input type="text" name="emailid" id="emailid" size="30"></td>

  </tr>
  <tr>
      
      <tr>

  <td>Sex</td>

  <td><input type="radio" name="sex" value="male" size="10">Male

  <input type="radio" name="sex" value="Female" size="10">Female</td>
  
  </tr>
  
  <tr>

  <td>Date of Birth</td>

  <td><input type="text" name="dob" id="dob" size="30"></td>

  </tr>


  <td>Address</td>

  <td><input type="text" name="paddress" id="paddress" size="30"></td>

  </tr>
  
  <tr>

  <td>City</td>

  <td><select name="City">

  <option value="-1" selected>select..</option>

  <option value="Denver">Denver</option>

  <option value="Golden Colorado">Golden Colorado</option>

  <option value="Parker">Parker</option>

  <option value="Arvada">Arvada</option>

  </select></td>

  </tr>

<tr>

  <td>State</td>

  <td><select name="District">

  <option value="-1" selected>select..</option>

  <option value="Colorado">Colorado</option>

  <option value="California">California</option>

  <option value="Texas">St.Antonio</option>

  <option value="Colorado">Denver</option>

  </select></td>


</tr>


  <tr>

  <td>ZipCode</td>

  <td><input type="text" name="pincode" id="ZipCode" size="30"></td>


</tr>

<tr>

  <td>Mobile No</td>

  <td><input type="text" name="mobileno" id="mobileno" size="30"></td>

  </tr>

  <tr>
      <tr>

  <td>User Access</td>

  <td><select name="Course">

  <option value="-1" selected>select..</option>

  <option value="Maintenance">Admin</option>

  <option value="Doctor">Doctor</option>

  <option value="Patient">Patient</option>

  <option value="Specialized">Specialized Doctor</option>

  </select></td>

  </tr>

  <td><input type="reset"></td>

  <td colspan="2"><input type="submit" value="Submit Form" /></td>

  </tr>

  </table>

  </form>

    <script>
    function validate()
{ 
   if( document.UserRegistration.textnames.value == "" )
   {
     alert( "Please provide your Name!" );
     document.UserRegistration.textnames.focus() ;
     return false;
   }
   if( document.UserRegistration.FirstName.value == "" )
   {
     alert( "Please provide your First Name!" );
     document.UserRegistration.firstname.focus() ;
     return false;
   }
   
   if( document.UserRegistration.address.value == "" )
   {
     alert( "Please provide your Address!" );
     document.UserRegistration.address.focus() ;
     return false;
   }
   if( document.UserRegistration.address.value == "" )
   {
     alert( "Please provide your Address!" );
     document.UserRegistration.address.focus() ;
     return false;
   }
   if ( ( UserRegistration.sex[0].checked == false ) && ( UserRegistration.sex[1].checked == false ) )
   {
   alert ( "Please choose your Gender: Male or Female or Not Wish to Identify" );
   return false;
   }   
   if( document.UserRegistration.City.value == "-1" )
   {
     alert( "Please provide your City!" );
     document.UserRegistration.City.focus() ;
     return false;
   }   
   if( document.UserRegistration.UserAccess.value == "-1" )
   {
     alert( "Please provide your UserAccess!" );
    
     return false;
   }   
   if( document.UserRegistration.City.value == "-1" )
   {
     alert( "Please provide your Select City!" );
    
     return false;
   }   
   if( document.UserRegistration.State.value == "-1" )
   {
     alert( "Please provide your Select State!" );
     
     return false;
   }
   if( document.UserRegistration.pincode.value == "" ||
           isNaN( document.UserRegistration.ZipCode.value) ||
           document.UserRegistration.ZipCode.value.length != 6 )
   {
     alert( "Please provide a ZinCode in the format ######." );
     document.UserRegistration.Zincode.focus() ;
     return false;
   }
 var email = document.UserRegistration.emailid.value;
  atpos = email.indexOf("@");
  dotpos = email.lastIndexOf(".");
 if (email == "" || atpos < 1 || ( dotpos - atpos < 2 )) 
 {
     alert("Please enter correct email ID")
     document.UserRegistration.emailid.focus() ;
     return false;
 }
  if( document.UserRegistration.Date of Birth.value == "" )
   {
     alert( "Please provide your Date of Birth!" );
     document.UserRegistration.dateofbirth.focus() ;
     return false;
   }
  if( document.UserRegistration.mobileno.value == "" ||
           isNaN( document.UserRegistration.mobileno.value) ||
           document.UserRegistration.mobileno.value.length != 10 )
   {
     alert( "Please provide a Mobile No in the format 123." );
     document.UserRegistration.mobileno.focus() ;
     return false;
   }
   return( true );
}
</script>
</body>
</html>
