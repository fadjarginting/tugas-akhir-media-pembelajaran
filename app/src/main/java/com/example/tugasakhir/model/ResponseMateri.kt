package com.example.tugasakhir

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

	@field:SerializedName("materi")
	val materi: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("namaFile")
	val namaFile: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("judul")
	val judul: String? = null,

	@field:SerializedName("gambar")
	val gambar: Any? = null,

	@field:SerializedName("link_vd")
	val linkVd: String? = null
)
