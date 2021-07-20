import org.apache.spark.sql.SparkSession

object SparkScalaDemo {
  def main(args: Array[String]): Unit = {
    println("Hello Spark Scala")

	System.setProperty("hadoop.home.dir","C:\\winutils")
	//.config("spark.sql.warehouse.dir",warehous.location)
	//spark-project-hive
    // Create a Spark Session
    val spark = SparkSession
      .builder
      .appName("HelloSpark")
      .config("spark.master", "local")
	  .enableHiveSupport()
      .getOrCreate()
    println("Created Spark Session")
    val sampleSeq = Seq((1,"spark"),(2,"Big Data"))

    val df = spark.createDataFrame(sampleSeq).toDF("course id", "course name")
    df.show()
	df.write.format(source="csv").save(path="samplesq")
	//winutils.exe chmod -R 777 \tmp\hive
	//Create Dataframe from PG table Coursetable
	val pgConnectionProperties = new Properties()
	pgConnectionProperties.put("user","postgres")
	pgConnectionProperties.put("password","admin")
	
	val pgTable = "futureschema.futurex_course_catalog"
	val pgCourseDataframe = spark.read.jdbc("jdbc:postgresql://localhost:5432/futurex",pgTable,pgConnectionProperties)
	pgCourseDataframe.show()

  }

}