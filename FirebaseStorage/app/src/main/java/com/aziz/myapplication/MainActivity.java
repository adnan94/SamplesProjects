package com.aziz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    StorageReference storegeRef,folderRef,imgRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.imageView);

//        DatabaseReference d = FirebaseDatabase.getInstance().getReference();
//        d.child("Adanna").push().setValue("Adii");


        storegeRef = FirebaseStorage.getInstance().getReference();


        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

        ((Button) findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                intent.setType("*/*");
//                Intent i = Intent.createChooser(intent, "File");
//                startActivityForResult(i, 1);

                Intent intent = new Intent();
//                chooseflag = false;
                //  intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {

            String path = getRealPathFromURI(data.getData());
            getImage(path,data);
        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String filePath = uri.getPath();
            uploadDocOrFile(filePath);
//            Log.d("TAG","inside file upload");
////
//            File f=new File(data.getData().toString());
//            Log.d("TAGii", f.getAbsolutePath());
//
//            String path = f.getAbsolutePath().toString();
//            Uri file = Uri.fromFile(new File(path));
//            StorageReference riversRef = storegeRef.child("file/");
//            UploadTask uploadTask = riversRef.putFile(file);
//            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(MainActivity.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
//                    Log.d("TAG", e.getMessage().toString());
//                }
//            });
////


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void getImage(String path,Intent data) {
        Uri file = Uri.fromFile(new File(path));
        Log.d("TAG", file.toString());

//        StorageReference riversRef = storegeRef.child("image/");
        File f=new File(path);

        imgRef = storegeRef.child("imageOnly").child(f.getName());

        UploadTask uploadTask = imgRef.putFile(file);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
                Log.d("TAG", e.getMessage().toString());
            }
        });

        try {
            Bitmap ii = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
            iv.setImageBitmap(ii);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadDocOrFile(String filePath) {


        File fileRef = new File(filePath);
        String filenew = fileRef.getName();

        int dot = filenew.lastIndexOf('.');
//        String base = (dot == -1) ? filenew : filenew.substring(0, dot);
//        String extension = (dot == -1) ? "" : filenew.substring(dot + 1);

//        AppLog.d("fileKanewName", base);
//        AppLog.d("fileKanewName", extension);
//        AppLog.d("fileKaTag", filePath);
//        Log.d("filesize", String.valueOf(fileRef.getTotalSpace()));

//long length = file.length();

//        mProgressDialog = ProgressDialog.show(getActivity(), "Sending File", "loading...", true, false);
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        long fileLenght = fileRef.length();
        fileLenght = fileLenght / 1024;

        System.out.println("File Path : " + fileRef.getPath() + ", File size : " + fileLenght + " KB");
        Log.d("uridata", filePath);

        final long FIVE_MEGABYTE = 1024 * 1024 * 5;

        fileLenght = fileLenght * 1024;
        UploadTask uploadTask;

        if (fileLenght <= FIVE_MEGABYTE) {

            Uri file = Uri.fromFile(new File(filePath));
//            AppLog.d("fileKaLastSeg", file.getLastPathSegment() + date);
//           StorageReference fileStorageRef = folderRef;
            folderRef = storegeRef.child("FilesOnly").child(filenew);
            uploadTask = folderRef.putFile(file);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//                    String downloadUrl = taskSnapshot.getDownloadUrl().toString();
                    Toast.makeText(MainActivity.this, "Sucess :::::::::" + taskSnapshot.getDownloadUrl().toString(), Toast.LENGTH_LONG).show();

//                    Log.d("DownloadURL", downloadUrl.toString());

//                    messageEditText.setText(downloadUrl);
//                    ChatService.sendMessage(mChannelItem.getGroupID(), mChannelItem.getSubgroupID(), mChannelItem.getID(), downloadUrl);
//
//                    mProgressDialog.dismiss();
//                    messageEditText.setText("");
//                    mProgressDialog.dismiss();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    System.out.println("Upload is " + progress + "% done");

                }
            }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                    System.out.println("Upload is paused");

                }
            });
        } else {
//            mProgressDialog.dismiss();
            Toast.makeText(MainActivity.this, "File size is too biG?", Toast.LENGTH_LONG).show();

        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
