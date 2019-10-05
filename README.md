# Pixin
An Image Caching Library that cache bitmap images within application session.

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.alitele:Pixin:1.0'
	}
**Usage:**

**Step : 1**

//Initializing Library once in Application Class onCreate
```
Pixin.instance.init(this@App);
```

**Step : 2**

//Load image
```
Pixin.instance.load(URL:String, Placeholder:Int, iv:ImageView)
```

**How it Looks!**


![alt text](https://github.com/alitele/Pixin/blob/master/sample.png)

Developer:Ali Akram

Contact: aliakram.me@hotmail.com