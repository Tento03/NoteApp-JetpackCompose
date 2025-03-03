package com.example.noteappcompose.module

import android.content.Context
import androidx.room.Room
import com.example.noteappcompose.db.NoteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesNoteDB(@ApplicationContext app: Context):NoteDB=Room.databaseBuilder(
        app.applicationContext,NoteDB::class.java,"NoteAppCompose"
    ).build()

    @Singleton
    @Provides
    fun providesNoteDao(db: NoteDB)=db.NoteDao()
}