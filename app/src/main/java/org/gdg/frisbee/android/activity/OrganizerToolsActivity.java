package org.gdg.frisbee.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.TextView;

import org.gdg.frisbee.android.R;

import roboguice.inject.InjectView;

public class OrganizerToolsActivity extends GdgActivity{

    private static final int REQUEST_BOOKMARKS = 1;

    @InjectView(R.id.media_url)
    private TextView mMediaUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_organizer_tools);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle(R.string.organizer_tools);

    }

    public void onPickBookmarkClick(View target){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(Browser.BOOKMARKS_URI);
        startActivityForResult(intent, REQUEST_BOOKMARKS);
    }

    public void onChromecastClick(View target){
        String url = mMediaUrl.getText().toString();

        //TODO call media router
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (responseCode == RESULT_OK){
            if (requestCode == REQUEST_BOOKMARKS){
                mMediaUrl.setText(intent.getData().toString());
            }
        }
    }
}
