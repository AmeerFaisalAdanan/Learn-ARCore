package com.example.helloar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {

    private ArFragment arfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add ar fragment from the ui

        arfragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arfragment);

            // when tap, put the AR asset on the plane

        arfragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            // hook the ar asset to the plane

            Anchor anchor = hitResult.createAnchor();


            //render model to the plane

            ModelRenderable.builder()

                    // get resources
                    .setSource(this, Uri.parse("gundam.sfb"))

                    //when the asset is loaded, render to the camera
                    .build()
                    // add the asset to the plane
                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable))

                    //if error, display error
                    .exceptionally(throwable -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage(throwable.getMessage())
                                .show();
                        return null;
                    });
        });
    }


        //    add model to the plane on tap
    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable){
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(arfragment.getTransformationSystem());
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        arfragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }
}
