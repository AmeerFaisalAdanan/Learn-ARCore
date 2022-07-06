# Issue regarding import sceneform assets <As of 7 April 2020>
Just to note that there are some error while importing the asset using Arcore Import Sceneform Asset. Import Sceneform Asset would fail to import the asset as of this time of Android Studio and ARCore version.

![workflow](https://github.com/AmeerFaisalAdanan/Learn-ARCore/actions/workflows/android.yml/badge.svg)



## Workaround
I found this workaround some time ago, cant rememeber where did i find it actually :D. Use this step to import sceneform asset.

1. Change your view to App structure
2. Create sampledata directory inside the app directory
3. Copy your assets/models inside this directory. (As of writing, I use .fbx and .obj format for the model)
4. Go to [Enable AR Core](https://developers.google.com/ar/develop/java/enable-arcore) to enable Ar Core to your project
5. Inside your app build.gradle, use this line of code to import your sceneform assets. You can import multiple asset by repeating the same code.

> sceneform.asset('sampledata/<yourassetname>',
        'default',
        'sampledata/<yourassetname.sfa>',
        'src/main/res/raw/<yourassetname>')

6. Rebuild the project
