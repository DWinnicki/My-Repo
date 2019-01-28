package com.davidwinnicki.myrepo.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("html_url")
    val htmlUrl: String
)