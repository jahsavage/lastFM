package com.discoid.testsavlastfm.view;

import com.discoid.testsavlastfm.R;

public enum SearchType {
    ARTIST(R.id.menu_search_by_artist), ALBUM(R.id.menu_search_by_album), TRACK(R.id.menu_search_by_track);

    private int menuId;

    private SearchType(int menuIt) {
        this.menuId = menuIt;
    }

    static SearchType match(int id) {
        for (SearchType searchType : SearchType.values()) {
            if (searchType.menuId == id) {
                return searchType;
            }
        }
        return ARTIST;
    }
}
