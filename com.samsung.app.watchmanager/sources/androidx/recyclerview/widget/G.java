package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.RecyclerView;

class G implements Parcelable.ClassLoaderCreator<RecyclerView.SavedState> {
    G() {
    }

    @Override // android.os.Parcelable.Creator
    public RecyclerView.SavedState createFromParcel(Parcel parcel) {
        return new RecyclerView.SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public RecyclerView.SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new RecyclerView.SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public RecyclerView.SavedState[] newArray(int i) {
        return new RecyclerView.SavedState[i];
    }
}
