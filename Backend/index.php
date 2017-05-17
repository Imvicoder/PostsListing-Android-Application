<?php
error_reporting(0);
require_once("Rest.inc.php");
	class API extends REST
	{
	
	   public $data = "";

		 const DB_SERVER = "localhost";
		 const DB_USER = "root";
		 const DB_PASSWORD = "";
		 const DB = "task";		

		private $db = NULL;

	
    public function __construct(){
            parent::__construct();              // Init parent contructor
            $this->dbConnect();                 // Initiate Database connection
        }   
        /*
         *  Database connection 
        */
        private function dbConnect(){
            $this->db = mysql_connect(self::DB_SERVER,self::DB_USER,self::DB_PASSWORD);
            if($this->db)
                mysql_select_db(self::DB,$this->db);
        }   
        /*
         * Public method for access api.
         * This method dynmically call the method based on the query string
         *
         */
        public function processApi(){
            $func = strtolower(trim(str_replace("/","",$_REQUEST['rquest'])));
            if((int)method_exists($this,$func) > 0)
                $this->$func();
            else
                $this->response('',404);                // If the method not exist with in this class, response would be "Page not found".
        }
		private function index()
		{
			$msg=array("API Vesion "=>1.3,"API Description "=>"The API use by the Desetation System");
			$this->response($this->json($msg),200);
		}
		private function reguser(){
			$email=$_POST["email"];
	 // if(isset($_FILES['image'])){
     // $errors= array();
      $file_name = $_FILES['imag']['name'];
      $file_size = $_FILES['imag']['size'];
	  $fir_dir='images/'.basename($_FILES['imag']['name']); 
	  
      $file_tmp = $_FILES['imag']['tmp_name'];
      $file_type = $_FILES['imag']['type'];
      $file_ext=strtolower(end(explode('.',$_FILES['imag']['name'])));
      
      $expensions= array("jpeg","jpg","png");
     /* 
      if(in_array($file_ext,$expensions)=== false){
         $errors[]="extension not allowed, please choose a JPEG or PNG file.";
      }
      
      if($file_size > 2097152) {
         $errors[]='File size must be excately 2 MB';
      }*/
      
     // if(empty($errors)==true) {
         if(move_uploaded_file($file_tmp,$fir_dir))	{
         $quer="insert into users(`email`,`dp`) values ('$email','$fir_dir')";
			if(mysql_query($quer))
			{
			$msg=array('response_code'=>100,'response_desc'=>"Success",'message'=>"registration Successfull");
			$this->response($this->json($msg),200);
			}
			else
			{
			$msg=array('response_code'=>102,'response_desc'=>"Failed",'message'=>"couldn't register");
			$this->response($this->json($msg),200);
				
			}			 
         echo "Success";
		 }else{
			 echo'failed';
		 }
     // }else{
      //   print_r($errors);
      //}
   }
   // $dp=$_POST["image"];
           
          
		
	    private function insertpost()
		{
			$email=$_POST["email"]; // sqare brackets k andar key h
			$post=$_POST["post"];
			$likes=rand(1,100);			 
			$quer="insert into posts(`email`,`post`,`likes`) values ('$email','$post','$likes')";
			if(mysql_query($quer))
			{
			$msg=array('response_code'=>100,'response_desc'=>"Success",'message'=>"post Successfull");
			$this->response($this->json($msg),200);
			}
			else
			{
			$msg=array('response_code'=>102,'response_desc'=>"Failed",'message'=>"couldn't post");
			$this->response($this->json($msg),200);
				
			}
			
		}
		private function fetchposts(){
			$query="Select users.dp ,posts.email,posts.post,posts.posted_at,posts.likes from users,posts where users.email=posts.email";
			
			$res=mysql_query($query);
			
			$data=array();
			//$con=mysql_num_rows($res);
			while($rest=mysql_fetch_assoc($res))
			{
				array_push($data,$rest);
			}
			//echo $con;
			$this->response($this->json($data),200);
		}
		private function fetchpostsbyid(){
			$emailid=$_POST["email"];
			$query="Select users.dp ,posts.email,posts.post,posts.posted_at,posts.likes from users,posts where users.email='$emailid' and  posts.email='$emailid'";
			$res=mysql_query($query);
			if(mysql_num_rows($res)>0){
			$data=array();
			//$con=mysql_num_rows($res);
			while($rest=mysql_fetch_assoc($res))
			{
				array_push($data,$rest);
			}
			//echo $con;
			$this->response($this->json($data),200);
			}
			else
				{
					$message=array("responce_code"=>103,"response desc"=>"your post","message"=>"No post exist for this id");
					$this->response($this->json($message));
				}
			
		}
		
	
//		public function guidevari($name,$)
        private function json($data){
            if(is_array($data)){
                return json_encode($data);
            }
        }
		
    }
    
    // Initiiate Library
    
    $api = new API;
    $api->processApi();
	?>	