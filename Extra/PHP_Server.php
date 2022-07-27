<?php
if(isset($_REQUEST["EmpID"])&&isset($_REQUEST["Password"])){
    try{
        $exsist="false! ";
        $conn = new mysqli('localhost','root','','inventorysystem');
        $qrstr="select * from employess where EmpID =".$_REQUEST["EmpID"];
        $res=$conn->query($qrstr);
        for($i=0;$i<$res->num_rows;$i++) {
            $row = $res->fetch_object();
            if ($row->Password == $_REQUEST["Password"]){ $exsist="true";
            echo $row->LAT;
            }
        }
        $conn->close();
        echo "!".$exsist;
    }
    catch (Exception $ex){
    }
}
elseif(isset($_REQUEST["ProdID"])&&isset($_REQUEST["Ammount"])&&isset($_REQUEST["status"])){
    $conn = new mysqli('localhost','root','','inventorysystem');
if($_REQUEST["status"]=="add"){
    try{
        $qrstr="SELECT * FROM `products` WHERE `ProdID`=".$_REQUEST["ProdID"].";";
        $res=$conn->query($qrstr);
        if($res->num_rows>0)echo "found";
        else echo "notfound";
        $qrstr="UPDATE products SET Ammount =Ammount+".$_REQUEST["Ammount"]." WHERE ProdID =".$_REQUEST["ProdID"];
        $res=$conn->query($qrstr);
    }
    catch (Exception $ex){
    }
}
elseif($_REQUEST["status"]=="delete"){
    try{
        $qrstr="SELECT * FROM `products` WHERE `ProdID`=".$_REQUEST["ProdID"].";";
        $res=$conn->query($qrstr);
        $row=$res->fetch_object();
        if($res->num_rows>0 &&$row->Ammount>=$_REQUEST["Ammount"]){
            $qrstr="UPDATE products SET Ammount =Ammount-".$_REQUEST["Ammount"]." WHERE ProdID =".$_REQUEST["ProdID"];
            $res=$conn->query($qrstr);
            echo "found";}
        elseif($res->num_rows>0 &&$row->Ammount<$_REQUEST["Ammount"]) echo "dhdhjh";
        else {echo "notfound";}
    }
    catch (Exception $ex){
    }
}
$conn->close();
}
elseif(isset($_REQUEST['ProdIDSearch'])){
    try{
        $conn = new mysqli('localhost','root','','inventorysystem');
        $qrstr="SELECT * FROM `products` WHERE `ProdID`=".$_REQUEST["ProdIDSearch"].";";
        $res=$conn->query($qrstr);
        if($res->num_rows>0){
            $result=$res->fetch_object();
            echo "found!".$result->ProdID.":".$result->Name .":".$result->Ammount .":".$result->PriceItem;}
        else echo "notfound! ";
        $conn->close();
    }
    catch (Exception $ex){
    }
}
elseif(isset($_REQUEST['show'])){
    try {
        $conn = new mysqli('localhost', 'root', '', 'inventorysystem');
        $qrstr = "SELECT * FROM `products`;";
        $res = $conn->query($qrstr);
        $data = "";
        for ($i = 0; $i < $res->num_rows; $i++) {
            $row = $res->fetch_object();
            $data.=$row->ProdID.":".$row->Name .":".$row->Ammount .":".$row->PriceItem."@";
        }
        echo $data;
        $conn->close();
    }catch (Exception $ex){
    }
}
elseif(isset($_REQUEST['Empselid'])){
    try{
        $conn = new mysqli('localhost','root','','inventorysystem');
        $qrstr="UPDATE employess SET LAT='". date('Y-m-d H:i:s')."'WHERE EmpID='".$_REQUEST['Empselid']."';";
        $res=$conn->query($qrstr);
        $conn->close();
    }
    catch (Exception $ex){
        echo $ex->getTraceAsString();
    }
}