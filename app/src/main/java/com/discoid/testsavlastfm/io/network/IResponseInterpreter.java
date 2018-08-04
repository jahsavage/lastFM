package com.discoid.testsavlastfm.io.network;

import java.io.IOException;

import retrofit2.Response;

/**
 * Categorizes errors and delivers then in a standard form
 */

public interface IResponseInterpreter {
    void filterErrors(Response response) throws ErrorCategoryException, IOException;
}
