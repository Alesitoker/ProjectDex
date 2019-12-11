
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeldItem implements Parcelable {

    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("version_details")
    @Expose
    private List<VersionDetail> versionDetails = null;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<VersionDetail> getVersionDetails() {
        return versionDetails;
    }

    public void setVersionDetails(List<VersionDetail> versionDetails) {
        this.versionDetails = versionDetails;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.item, flags);
        dest.writeList(this.versionDetails);
    }

    public HeldItem() {
    }

    protected HeldItem(Parcel in) {
        this.item = in.readParcelable(Item.class.getClassLoader());
        this.versionDetails = new ArrayList<VersionDetail>();
        in.readList(this.versionDetails, VersionDetail.class.getClassLoader());
    }

    public static final Parcelable.Creator<HeldItem> CREATOR = new Parcelable.Creator<HeldItem>() {
        @Override
        public HeldItem createFromParcel(Parcel source) {
            return new HeldItem(source);
        }

        @Override
        public HeldItem[] newArray(int size) {
            return new HeldItem[size];
        }
    };
}
