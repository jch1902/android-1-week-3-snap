package com.ucsdextandroid1.snapapp.stories;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.snapapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 2019-04-20
 */
public class StoriesAdapter extends RecyclerView.Adapter {

    private List<StoriesListItem> items = new ArrayList<>();

    private StoryCardViewHolder.StoryCardClickListener listener;

    public void setItems(Context context, List<Story> stories) {
        items.clear();

        // add title item, using context.getString(R.string.stories)) to get the title
        items.add(new StoriesListItem(context.getString(R.string.stories)));
        //TODO add all of the story items to the list
        for (Story story : stories) {
            items.add(new StoriesListItem(story));
        }
        // let the adapter know that  the data has changed
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            case StoriesListItem.TYPE_TITLE:
                return StoriesSectionTitleViewHolder.inflate(parent);
            case StoriesListItem.TYPE_STORY:
                return StoryCardViewHolder.inflate(parent);
            default :
                throw new IllegalArgumentException("Undefined argument");
        }
       // return StoryCardViewHolder.inflate(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO bind the title or the story to the correct view holder
        if(holder instanceof StoryCardViewHolder){
            ((StoryCardViewHolder) holder).bind(items.get(position).getStory());
        }else if(holder instanceof StoriesSectionTitleViewHolder){
            ((StoriesSectionTitleViewHolder) holder).bind(items.get(position).getTitle());
        }
    }
    @Override
    public int getItemCount() {
        // TODO return the correct item count
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        //TODO return the correct view type
        return items.get(position).getType();
    }

    // add a method that returns the correct span for each item type.
    public int getSpanSize(int position){
        switch(getItemViewType(position)){
            case StoriesListItem.TYPE_TITLE:
                return 1;
            case StoriesListItem.TYPE_STORY:
                return 2;
        }
        return 0;
    }

    // add a custom interface called Callback that extends the click listener defined on the StoriesCardViewHolder
    public void StoryCardClickListener(StoryCardViewHolder.StoryCardClickListener listener){
        this.listener = listener;
    }
    private class StoriesListItem {

        public static final int TYPE_TITLE = 1;
        public static final int TYPE_STORY = 2;

        private String title;
        private Story story;
        private int type;

        public StoriesListItem(String title){
            this.title = title;
            this.story = null;
            this.type = TYPE_TITLE;
        }

        public StoriesListItem(Story story){
            this.title = null;
            this.story = story;
            this.type = TYPE_STORY;
        }

        public String getTitle() {
            return title;
        }

        public Story getStory() {
            return story;
        }

        public int getType() {
            return type;
        }
    }

}
