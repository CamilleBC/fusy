package com.camillebc.fusy.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface TagDao {
    @Query("select * from tag")
    fun getAllTags(): List<Tag>

    @Query("select * from tag where warning = :warning")
    fun getWarnings(warning: Boolean = true): List<Tag>

    @Query("select * from tag where id = :id limit 1")
    fun getTag(id: Long): Tag

    @Insert(onConflict = REPLACE)
    fun insertTag(tag: Tag)

    @Insert(onConflict = REPLACE)
    fun insertTags(tagList: List<Tag>)

    @Update(onConflict = REPLACE)
    fun updateTag(tag: Tag)

    @Update(onConflict = REPLACE)
    fun updateTags(tagList: List<Tag>)

    @Delete
    fun deleteTag(tag: Tag)
}