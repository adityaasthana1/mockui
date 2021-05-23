package com.aditya.backend2.models.Post;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class PostData implements Parcelable {
    public Owner owner;
    public String id;
    public String image;
    public Date publishDate;
    public String text;
    public List<String> tags;
    public String link;
    public int likes;

    public PostData() {
    }

    public PostData(Owner owner, String id, String image, Date publishDate, String text, List<String> tags, String link, int likes) {
        this.owner = owner;
        this.id = id;
        this.image = image;
        this.publishDate = publishDate;
        this.text = text;
        this.tags = tags;
        this.link = link;
        this.likes = likes;
    }

    protected PostData(Parcel in) {
        id = in.readString();
        image = in.readString();
        text = in.readString();
        tags = in.createStringArrayList();
        link = in.readString();
        likes = in.readInt();
    }

    public static final Creator<PostData> CREATOR = new Creator<PostData>() {
        @Override
        public PostData createFromParcel(Parcel in) {
            return new PostData(in);
        }

        @Override
        public PostData[] newArray(int size) {
            return new PostData[size];
        }
    };

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(image);
        dest.writeString(text);
        dest.writeStringList(tags);
        dest.writeString(link);
        dest.writeInt(likes);
    }
}
