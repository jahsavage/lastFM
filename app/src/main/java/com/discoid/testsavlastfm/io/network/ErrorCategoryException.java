package com.discoid.testsavlastfm.io.network;


public class ErrorCategoryException extends Exception {

    private ErrorCategory mErrorCategory;
    private String mErrorBody;

    public ErrorCategoryException(ErrorCategory errorCategory, String errorBody) {
        mErrorCategory = errorCategory;
        mErrorBody = errorBody;
    }

    public ErrorCategoryException(ErrorCategory errorCategory) {
        this(errorCategory, null);
    }

    public ErrorCategoryException(Throwable throwable, ErrorCategory errorCategory) {
        super(throwable);
        mErrorCategory = errorCategory;
    }

    public ErrorCategory getErrorCategory() {
        return mErrorCategory;
    }

    public void setErrorCategory(ErrorCategory errorCategory) {
        mErrorCategory = errorCategory;
    }

    public String getErrorBody() {
        return mErrorBody;
    }

    public void setErrorBody(String errorBody) {
        mErrorBody = errorBody;
    }
}
