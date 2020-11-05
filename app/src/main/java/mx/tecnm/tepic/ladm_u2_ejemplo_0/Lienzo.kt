package mx.tecnm.tepic.ladm_u2_ejemplo_0

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Lienzo (p:MainActivity) : View(p){
    //val pantalla=p
    var contadorHilo=0
    var hilito=Hilo(this)
    override fun onDraw(c: Canvas) {
        super.onDraw(c)
        val p=Paint()
        c.drawARGB(100,178,239,135)
        p.textSize=60f
        c.drawText("Numero ejemplo: "+contadorHilo,100f,100f,p)
        p.style=Paint.Style.STROKE
        p.strokeWidth=7f
        p.setColor(Color.BLACK)
        c.drawCircle(width/2f,height/2f,contadorHilo.toFloat(),p)
     try{
         hilito.start()
    }catch (e:Exception){
         println(e.toString())
        //AlertDialog.Builder(pantalla).setMessage("El hilo no se puede volver a ejecutar")
          //  .setTitle("Aviso").show()
    }

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        //event.action=PRESIONE,ARRASTRO,LIBERO
        //event.x event.y
        if(event.action== MotionEvent.ACTION_DOWN){
            hilito.pausar()
        }//entra si se presiono
        if(event.action== MotionEvent.ACTION_MOVE){
            hilito.terminarHilo()
        }//entra si se arrastra
        if(event.action== MotionEvent.ACTION_UP){
        }//entra si se libero
        invalidate()//Vuelve a llamar al Ondraw y repinta
        return true
    }

}
class Hilo(p:Lienzo):Thread(){
    var puntero = p//Existe solo en esta linea.
    var mantener = true
    var despausado=true
    var may=true
    fun pausar(){
        despausado=!despausado
    }
    fun terminarHilo(){
        mantener=false
    }
    override fun run(){
        super.run()
        //Realmente se ejecuta una vez en segundo plano.
        while(mantener) {
            if (despausado == true) {
                    //puntero.text_1.text = "Hilo: " + puntero.contadorHilo
                puntero.run {
                    if(may){
                    sleep(10)
                    puntero.contadorHilo++
                    puntero.invalidate()
                    if(puntero.contadorHilo==200){
                        may=false
                    }
                }else{
                    sleep(10)
                    puntero.contadorHilo--
                    puntero.invalidate()
                    if(puntero.contadorHilo==0){
                        may=true
                    }
                } }
                }

            }
            sleep(2000)
        }
    }
