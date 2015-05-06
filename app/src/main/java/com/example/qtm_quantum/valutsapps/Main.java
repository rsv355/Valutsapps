package com.example.qtm_quantum.valutsapps;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Main extends Activity {

    private Cursor videoCursor;
    private int videoColumnIndex;
    ListView videolist;
    int count;
    String thumbPath;

    String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA,MediaStore.Video.Thumbnails.VIDEO_ID };
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization()
    {
        System.gc();
        String[] videoProjection = { MediaStore.Video.Media._ID,MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,MediaStore.Video.Media.SIZE };
        videoCursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,videoProjection, null, null, null);
        count = videoCursor.getCount();
        videolist = (ListView) findViewById(R.id.listView1);

        videolist.setAdapter(new VideoListAdapter(this.getApplicationContext()));
        videolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.gc();
                videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                videoCursor.moveToPosition(position);
                String filename = videoCursor.getString(videoColumnIndex);
                Log.i("FileName: ", filename);
                //Intent intent = new Intent(VideoActivity.this, ViewVideo.class);
                //intent.putExtra("videofilename", filename);
                //startActivity(intent);

            }
        });

    }


    public class VideoListAdapter extends BaseAdapter
    {
        private Context vContext;
        int layoutResourceId;

        public VideoListAdapter(Context c)
        {
            vContext = c;
        }

        public int getCount()
        {
            return videoCursor.getCount();
        }

        public Object getItem(int position)
        {
            return position;
        }

        public long getItemId(int position)
        {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent)
        {
            View listItemRow = null;
            listItemRow = LayoutInflater.from(vContext).inflate(R.layout.second, parent, false);

            TextView txtTitle = (TextView)listItemRow.findViewById(R.id.textView1);
//	TextView txtSize = (TextView)listItemRow.findViewById(R.id.txtSize);
//	ImageView thumbImage = (ImageView)listItemRow.findViewById(R.id.);

            videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            videoCursor.moveToPosition(position);
            txtTitle.setText(videoCursor.getString(videoColumnIndex));

//	videoColumnIndex = videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
//	videoCursor.moveToPosition(position);
//	txtSize.setText(" Size(KB):" + videoCursor.getString(videoColumnIndex));

            int videoId = videoCursor.getInt(videoCursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
            Cursor videoThumbnailCursor = managedQuery(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                    thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID+ "=" + videoId, null, null);

            if (videoThumbnailCursor.moveToFirst())
            {
                thumbPath = videoThumbnailCursor.getString(videoThumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
                Log.i("ThumbPath: ",thumbPath);

            }
//	thumbImage.setImageURI(Uri.parse(thumbPath));

            return listItemRow;

        }



    }


}