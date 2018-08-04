
package com.discoid.testsavlastfm.io.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

    @SerializedName("results")
    @Expose
    private Results_ results;

    public Results_ getResults() {
        return results;
    }

    public void setResults(Results_ results) {
        this.results = results;
    }

}
