package blink

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec


class BlinkTest extends AnyFlatSpec with ChiselScalatestTester {
  
    "Blink" should "blink" in {
        test(new Blink){dut =>
            dut.io.divider.poke(2.U)
            // println("LED state: "+ dut.io.out.peek())
            dut.io.out.expect(0.U)
            dut.clock.step()
            // println("LED state: "+ dut.io.out.peek())
            dut.io.out.expect(1.U)
            dut.clock.step()
            // println("LED state: "+ dut.io.out.peek())
            dut.io.out.expect(0.U)
            dut.clock.step()
            // println("LED state: "+ dut.io.out.peek())        
            dut.io.out.expect(1.U)
        
        }
    }

    
    "Blink" should "wavefile" in {
        test(new Blink).withAnnotations(Seq(WriteVcdAnnotation)){dut => 
            dut.io.divider.poke(4.U)
            dut.clock.step(10)
        }
    }







}
