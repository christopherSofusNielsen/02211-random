package blink

import chisel3._

class Blink() extends Module{

    val io=IO(new Bundle{
        val divider=Input(UInt(31.W))
        val out=Output(UInt(1.W))
    })

    val stateReg = RegInit(0.U)
    val cntReg = RegInit(0.U(32.W))

    val CNT_MAX=  io.divider/2.U-1.U
    //( fac / 2 - 1).U
    cntReg := cntReg + 1.U
    
    when(cntReg === CNT_MAX){
        cntReg := 0.U
        stateReg := ~stateReg
    }
    
    io.out := stateReg
}

object BlinkMain extends App {
  println("Generating the Blink hardware")
  emitVerilog(new Blink(), Array("--target-dir", "generated"))
}
