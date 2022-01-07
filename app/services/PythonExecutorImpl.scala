package services

import java.io.{ByteArrayOutputStream, IOException}

import models.DenseVector
import org.apache.commons.exec.{CommandLine, DefaultExecutor, PumpStreamHandler}

trait PythonExecutor {
  def generateVector(designation: String, categoryTitle: String): Seq[String]
}

class PythonExecutorImpl extends PythonExecutor {

  override def generateVector(designation: String, categoryTitle: String):  Seq[String] = {
    val line: String = "python3 /home/local/PRICEMINISTER/fatiha.reja/python/Semantic_Model_2_2.py \"" + designation
    .replaceAll("\"", "")+ "\" \"" + categoryTitle.replaceAll("\"", "") + "\""
    System.out.println("[calling : " + line +" ]")
    val cmdLine: CommandLine = CommandLine.parse(line)

    val outputStream: ByteArrayOutputStream = new ByteArrayOutputStream
    val streamHandler: PumpStreamHandler = new PumpStreamHandler(outputStream)

    val executor: DefaultExecutor = new DefaultExecutor
    executor.setStreamHandler(streamHandler)
    try executor.execute(cmdLine)
    catch {
      case ioException: IOException =>
        println("error while executing script" + ioException.getMessage, ioException)
        Thread.sleep(3000)
        executor.execute(cmdLine)
      case error: Throwable =>

        println("error while executing script" + error.getMessage, error)
        Thread.sleep(3000)
        executor.execute(cmdLine)
    }

    val resp1: Seq[String] = outputStream.toString
      .replace("[ ", "")
      .replace("[", "")
      .replace("]", "")
      .replaceAll("\\s+", ",").trim.split(",")

      //val resp = resp1.filter(!_.equals("")).map(s => s.toFloat)
    println(s"resp.size = ${resp1.size}")
    resp1
  }
}
