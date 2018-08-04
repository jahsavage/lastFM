package com.discoid.testsavlastfm.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import timber.log.Timber;

/**
 * Created by jahsavage on 22/09/2016.
 */
public class PicassoHelper {

    private static Picasso mPicasso;

    private static Picasso.Listener mPicassoErrorListener = new Picasso.Listener() {
        @Override
        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
            Timber.w("url %s, error %s",uri,exception.getMessage());
        }
    };

    /**
     * Fetches thumbnial or display an empty image
     *
     * @param context
     * @param thumbUrl
     * @param searchType
     * @param iv
     */
    public static void fetchThumbnail(Context context, String thumbUrl, SearchType searchType, ImageView iv) {

        if (TextUtils.isEmpty(thumbUrl)) {
            iv.setImageDrawable(null);
        } else {
            getPicasso(context).load(thumbUrl).into(iv);
        }
    }

    /**
     * Returns one with error reporting
     * @param context
     * @return
     */
    public static Picasso getPicasso(Context context) {

        if (mPicasso == null) {
            mPicasso = new Picasso.Builder(context)
                    //    .listener(mPicassoErrorListener)
                    .build();
        }

        return mPicasso;
    }

//    public static Drawable getErrorImage(Context context, SearchType searchType) {
//        return ResourcesCompat.getDrawable(context.getResources(), searchType.getNoImgResId(), null);
//    }
}

