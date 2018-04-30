package com.recrutify.rgc.mobileassistant.overlay

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.graphics.PixelFormat
import android.graphics.drawable.Drawable
import android.telephony.TelephonyManager
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.recrutify.rgc.mobileassistant.R
import de.hdodenhof.circleimageview.CircleImageView

class IncomingCallReceiver : BroadcastReceiver() {

    var isMoving: Boolean = false

    companion object {
        var rgcView: View? = null
        var candidate: CandidateContainer? = null
    }


    private var _xDelta: Int = 0
    private var _yDelta: Int = 0

    fun dpToPx(context: Context, dp: Int): Int {
        val density = context.getResources()
                .getDisplayMetrics()
                .density
        return Math.round(dp.toFloat() * density)
    }

    override fun onReceive(context: Context, intent: Intent) {

        Log.i("RGC.START", "-------------------")

        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE); //RINGING/OFFHOOK/IDLE

        when(state) {

            "RINGING" -> {

                val type = if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                else
                    WindowManager.LayoutParams.TYPE_SYSTEM_ERROR

                val params = WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        dpToPx(context, 120), type,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        //or WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                        , PixelFormat.TRANSLUCENT)

                params.gravity = Gravity.NO_GRAVITY


                val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                rgcView = inflater.inflate(R.layout.overlay_call_view, null)

                val avatarImage = rgcView?.findViewById<CircleImageView>(R.id.avatarImage)

                val closeButton = rgcView?.findViewById<Button>(R.id.btnClose)

                closeButton?.setOnClickListener({
                    if(rgcView != null) {
                        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                        wm.removeView(rgcView)
                        rgcView = null;
                    }


                })

                rgcView?.setOnTouchListener(object : View.OnTouchListener {
                    override fun onTouch(v: View?, event: MotionEvent): Boolean {

                        val X = event.rawX.toInt()
                        val Y = event.rawY.toInt()
                        when (event.action and MotionEvent.ACTION_MASK) {
                            MotionEvent.ACTION_DOWN -> {
                                Log.i("ACTION_DOWN", "!")
                                val lParams = v?.layoutParams as WindowManager.LayoutParams
                                //_xDelta = X - lParams.
                                _yDelta = Y - lParams.y
                            }
                            MotionEvent.ACTION_UP -> {
                                Log.i("ACTION_UP", "!")
                            }
                            MotionEvent.ACTION_POINTER_DOWN -> {
                                Log.i("ACTION_POINTER_DOWN", "!")
                            }
                            MotionEvent.ACTION_POINTER_UP -> {
                                Log.i("ACTION_POINTER_UP", "!")
                            }
                            MotionEvent.ACTION_MOVE -> {
                                Log.i("ACTION_MOVE", "!")
                                val layoutParams = v?.layoutParams as WindowManager.LayoutParams
                                layoutParams.y = Y - _yDelta
                                v.setLayoutParams(layoutParams)

                                val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                                wm.updateViewLayout(v, layoutParams)
                            }

                        }
                        return true;
                    }
                })

                val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                wm.addView(rgcView, params)

                Glide.with(context)
                        .load("https://images.unsplash.com/photo-1507120878965-54b2d3939100?auto=format&fit=crop&w=3744&q=80")
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                                avatarImage?.setImageResource(R.drawable.ic_boss_1)
                                return true
                            }

                            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                return false
                            }
                        })
                        .into(avatarImage as ImageView)

            }

            "IDLE" -> {

                if(rgcView != null){
                    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                    wm.removeView(rgcView)
                }
                rgcView = null;

            }
        }


    }
}
