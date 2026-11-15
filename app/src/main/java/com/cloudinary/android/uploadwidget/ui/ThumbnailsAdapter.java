package com.cloudinary.android.uploadwidget.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.cloudinary.android.ui.R;
import com.cloudinary.android.uploadwidget.model.BitmapManager;
import com.cloudinary.android.uploadwidget.model.Dimensions;
import com.cloudinary.android.uploadwidget.utils.MediaType;
import com.cloudinary.android.uploadwidget.utils.UriUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes11.dex */
class ThumbnailsAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {
    private Callback callback;
    private ArrayList<Uri> imagesUris;
    private int selectedThumbnailPosition;

    public interface Callback {
        void onThumbnailClicked(Uri uri);
    }

    public ThumbnailsAdapter(ArrayList<Uri> imagesUris, Callback callback) {
        this.imagesUris = imagesUris;
        this.callback = callback;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumbnail_list_item, parent, false);
        return new ThumbnailViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ThumbnailViewHolder holder, final int position) {
        final Uri uri = this.imagesUris.get(position);
        if (position == this.selectedThumbnailPosition) {
            holder.imageView.setBackgroundResource(R.drawable.selected_thumbnail_border);
        } else {
            holder.imageView.setBackgroundResource(0);
        }
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.imageView.setImageBitmap(null);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.cloudinary.android.uploadwidget.ui.ThumbnailsAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ThumbnailsAdapter.this.notifyItemChanged(ThumbnailsAdapter.this.selectedThumbnailPosition);
                ThumbnailsAdapter.this.selectedThumbnailPosition = holder.getAdapterPosition();
                ThumbnailsAdapter.this.notifyItemChanged(ThumbnailsAdapter.this.selectedThumbnailPosition);
                ThumbnailsAdapter.this.callback.onThumbnailClicked(uri);
            }
        });
        Context context = holder.itemView.getContext();
        int thumbnailSize = (int) context.getResources().getDimension(R.dimen.thumbnail_size);
        MediaType mediaType = UriUtils.getMediaType(context, uri);
        if (mediaType == MediaType.IMAGE) {
            holder.mediaTypeIcon.setVisibility(8);
            BitmapManager.get().load(context, uri, thumbnailSize, thumbnailSize, new BitmapManager.LoadCallback() { // from class: com.cloudinary.android.uploadwidget.ui.ThumbnailsAdapter.2
                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.LoadCallback
                public void onSuccess(Bitmap bitmap, Dimensions originalDimensions) {
                    if (holder.getAdapterPosition() == position) {
                        holder.imageView.setImageBitmap(bitmap);
                    }
                }

                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.LoadCallback
                public void onFailure() {
                }
            });
        } else if (mediaType == MediaType.VIDEO) {
            holder.mediaTypeIcon.setVisibility(0);
            holder.mediaTypeIcon.setImageResource(R.drawable.video);
            BitmapManager.get().thumbnail(context, uri, thumbnailSize, thumbnailSize, new BitmapManager.LoadCallback() { // from class: com.cloudinary.android.uploadwidget.ui.ThumbnailsAdapter.3
                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.LoadCallback
                public void onSuccess(Bitmap bitmap, Dimensions originalDimensions) {
                    if (holder.getAdapterPosition() == position) {
                        holder.imageView.setImageBitmap(bitmap);
                    }
                }

                @Override // com.cloudinary.android.uploadwidget.model.BitmapManager.LoadCallback
                public void onFailure() {
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.imagesUris.size();
    }

    public void setSelectedThumbnail(int position) {
        notifyItemChanged(this.selectedThumbnailPosition);
        notifyItemChanged(position);
        this.selectedThumbnailPosition = position;
    }

    static class ThumbnailViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView mediaTypeIcon;

        ThumbnailViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.mediaTypeIcon = (ImageView) itemView.findViewById(R.id.mediaTypeIcon);
        }
    }
}
