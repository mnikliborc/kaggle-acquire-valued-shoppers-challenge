package shp

import java.text.MessageFormat

class TimeMeter private (val label: String, val startMillis: Long) {

  def stop(): Unit = {
    if (TimeMeter.active) {
      val measurement = System.currentTimeMillis() - startMillis;
      val msg = String.format("%s - %sms", label, measurement.toString);
      println(msg);
    }
  }
}

object TimeMeter {
  var active = false

  def init(): Unit = {
    active = true
  }

  def start(label: String) = new TimeMeter(label, System.currentTimeMillis())
}