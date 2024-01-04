package com.example.tugasakhir.model

import com.google.gson.annotations.SerializedName

data class ResponseMateri(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class DataItem(

	@field:SerializedName("cover")
	val cover: String? = null,

	@field:SerializedName("infografis")
	val infografis: String? = null,

	@field:SerializedName("bab")
	val bab: String? = null,

	@field:SerializedName("link_video")
	val linkVideo: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("keyword")
	val keyword: String? = null,

	@field:SerializedName("filepdf")
	val filepdf: String? = null
)
