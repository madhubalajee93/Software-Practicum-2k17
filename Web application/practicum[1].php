<?php

$host="localhost";
$user="root";
$password="";
$db="patient";

mysql_connect($host,$user,$password);
mysql_select_db($db);

if(isset($_POST['First Name'])){
    $fname=$_POST['First Name'];
    $lname=$_POST['Last Name'];
    
    $sql="select * from loginform where FirstName='".$fname."'AND LastName='".$lname."' limit 1";
    
    $result=mysql_query($sql);
    
    if(mysql_num_rows($result)==1){
        echo "You Have successfully Created Account";
        exit();
    }
    else{
        echo "You already have an existing account";
        exit();
    }
}
?>