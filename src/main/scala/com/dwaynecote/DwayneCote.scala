package com.dwaynecote

import org.odfi.indesign.core.module.IndesignModule
import org.odfi.indesign.core.main.IndesignPlatorm
import org.odfi.indesign.core.config.ooxoo.OOXOOConfigModule
import java.io.File

object DwayneCote extends App {

  IndesignPlatorm.prepareDefault

  IndesignPlatorm use OOXOOConfigModule
  OOXOOConfigModule.configFolder = new File("dwayne-config")

  IndesignPlatorm use DCSite

  IndesignPlatorm.start

}