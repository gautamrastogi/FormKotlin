package com.example.formkotlinpj

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.adapter_image.*



class MainActivity : AppCompatActivity(), View.OnClickListener {
    var context: Context? = null
    var PICK_IMAGE_MULTIPLE = 1
    lateinit var imagePath: String

    lateinit var imageView: ImageView
    lateinit var hsvImage: RecyclerView
    lateinit var buttons: ImageView
    private val pickImage = 100
    private var imageUri: Uri? = null
    val REQUEST_CODE = 200
    private val imagesPathList = ArrayList<Uri>()
     private lateinit var adapter :ImageAdpater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener(this)
        title = "Register Complaint"
        imageView = findViewById(R.id.imageView)
        buttons = findViewById(R.id.button2)

        hsvImage=findViewById(R.id.hsvImage)
        //imagesPathList = ArrayList<Uri>()

        //var myListData = ArrayList<Uri>();
        //imagesPathList.add(R.drawable.iconfinder_icon_camera_211634)
//        //myListData.add(selectedImage)



        
         adapter = ImageAdpater(imagesPathList)
        hsvImage.setHasFixedSize(true)
        hsvImage.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        hsvImage.adapter = adapter


        buttons.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 1)
//            if (Build.VERSION.SDK_INT < 19) {
//                val intent = Intent()
//                intent.type = "image/*"
//                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//                intent.action = Intent.ACTION_GET_CONTENT
//                startActivityForResult(
//                    Intent.createChooser(intent, "Select Picture")
//                    , PICK_IMAGE_MULTIPLE
//                )
//            } else {
//                val intent = Intent(Intent.ACTION_PICK)
//               // intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//                //intent.addCategory(Intent.CATEGORY_OPENABLE)
//                intent.type = "image/*"
//                this.startActivityForResult(intent, 1)
//            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: resultCode "+resultCode+" requestCode"+requestCode)

        if (resultCode == RESULT_OK && requestCode == 1) {


          //  val imageUri: Uri = data.clipData!!.getItemAt(0).uri

                    //Log.d(TAG, "onActivityResult: imageUri"+imageUri)
            Log.d(TAG, "onActivityResult: inside if befor"+imagesPathList.size)
                    imagesPathList.add( data!!.data!!)
                    imagesPathList.add( data.data!!)

            Log.d(TAG, "onActivityResult: inside if "+imagesPathList.size)
                    adapter.updateUI(imagesPathList)
            //imageUri = data?.data
            //imageView.setImageURI(imageUri)
        }
    }
//    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
//            Log.d(TAG, "onActivityResult: inside")
//                if (data?.clipData != null) {
//                    val count = data.clipData?.itemCount
//                    val imageUri: Uri = data.clipData!!.getItemAt(0).uri
//
//                    Log.d(TAG, "onActivityResult: imageUri"+imageUri)
//                    imagesPathList.add(imageUri)
//                    adapter.updateUI(imagesPathList)
//                    Log.d(TAG, "$imagesPathList is the path ")
////                    for (i in 0 until count!!) {
////
////
////
////
////                    }
//                    //ivDynamicImage.setImageURI(imagesPathList[0])
//                }
//                else {
//                    val imageUri = data?.data
//
//                    Log.d(TAG, "elsee is the path ")
//                    //ivDynamicImage.setImageURI(imageUri)
//                }
//            }
//        else
//        {
//            Log.d(TAG, "onActivityResult: inside else")
//        }
//        }



//     fun getRealPathFromURI(context: Context, contentUri: Uri): String? {
//        var cursor: Cursor? = null
//        return try {
//            val proj = arrayOf(MediaStore.Images.Media.DATA)
//            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
//            val column_index: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//            cursor.moveToFirst()
//            cursor.getString(column_index)
//        } catch (e: Exception) {
//            Log.e(TAG, "getRealPathFromURI Exception : $e")
//            ""
//        } finally {
//            cursor?.close()
//        }
//    }


    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.button -> {
                if(validate()) {
                    Toast.makeText(applicationContext, "Submitted", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun validate(): Boolean {
        if(editTextNumber.text.toString().isEmpty()){
            editTextNumber.error = "Resident Number should not be blank"
            return false
        }
        else if(editTextTextMultiLine.text.toString().isEmpty()){
            editTextTextMultiLine.error = "Complaint detail should not be blank"
            return false
        }
        return true
    }




}















