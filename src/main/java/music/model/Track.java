/*
 * Copyright (C) 2014 Siegenthaler Solutions.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package music.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Track extends SimpleTrack {
    @SerializedName("album")
    private SimpleAlbum mAlbum;

    @SerializedName("external_ids")
    private Map<String, String> mExternalIds;

    @SerializedName("popularity")
    private int mPopularity;

    /**
     * (non-doc)
     */
    public SimpleAlbum getAlbum() {
        return mAlbum;
    }

    /**
     * (non-doc)
     */
    final public Map<String, String> getExternalIds() {
        return mExternalIds;
    }

    /**
     * (non-doc)
     */
    public int getPopularity() {
        return mPopularity;
    }
}
