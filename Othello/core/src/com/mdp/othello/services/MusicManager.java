package com.mdp.othello.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;
import com.mdp.othello.OthelloGame;

/**
 * A service that manages the background music.
 * <p>
 * Only one music may be playing at a given time.
 */
public class MusicManager
    implements
        Disposable
{


    /**
     * The available music files.
     */
    public enum OurMusic
    {
        MENU( "music/menu.ogg" ),
        LEVEL( "music/level.ogg" );

        private String fileName;
        private Music musicResource;

        private OurMusic(
                String fileName)
        {
            this.fileName = fileName;
        }

        public String getFileName()
        {
            return fileName;
        }

        public Music getMusicResource()
        {
            return musicResource;
        }

        public void setMusicResource(
            Music musicBeingPlayed )
        {
            this.musicResource = musicBeingPlayed;
        }
    }

    /**
     * Holds the music currently being played, if any.
     */
    private OurMusic musicBeingPlayed;

    /**
     * The volume to be set on the music.
     */
    private float volume = 1f;

    /**
     * Whether the music is enabled.
     */
    private boolean enabled = true;

    /**
     * Creates the music manager.
     */
    public MusicManager()
    {
    }

    /**
     * Plays the given music (starts the streaming).
     * <p>
     * If there is already a music being played it is stopped automatically.
     */
    public void play(
        OurMusic music )
    {
        // check if the music is enabled
        if( ! enabled ) return;

        // check if the given music is already being played
        if( musicBeingPlayed == music ) return;

        // do some logging
        Gdx.app.log( OthelloGame.LOG, "Playing music: " + music.name() );

        // stop any music being played
        stop();

        // start streaming the new music
        FileHandle musicFile = Gdx.files.internal( music.getFileName() );
        Music musicResource = Gdx.audio.newMusic( musicFile );
        musicResource.setVolume( volume );
        musicResource.setLooping( true );
        musicResource.play();

        // set the music being played
        musicBeingPlayed = music;
        musicBeingPlayed.setMusicResource( musicResource );
    }

    public void pause() {
        if(musicBeingPlayed != null){
            Gdx.app.log( OthelloGame.LOG, "Pausing current music" );
            Music musicResource = musicBeingPlayed.getMusicResource();
            musicResource.pause();
        }
    }
    public void resume(){
        if(musicBeingPlayed != null){
            Music musicResource = musicBeingPlayed.getMusicResource();
            if(!musicResource.isPlaying()){
                Gdx.app.log( OthelloGame.LOG, "Resuming current music" );
                musicResource.play();
            }
        }
    }
    /**
     * Stops and disposes the current music being played, if any.
     */
    public void stop()
    {
        if( musicBeingPlayed != null ) {
            Gdx.app.log( OthelloGame.LOG, "Stopping current music" );
            Music musicResource = musicBeingPlayed.getMusicResource();
            musicResource.stop();
            musicResource.dispose();
            musicBeingPlayed = null;
        }
    }

    /**
     * Sets the music volume which must be inside the range [0,1].
     */
    public void setVolume(
        float volume )
    {
        Gdx.app.log( OthelloGame.LOG, "Adjusting music volume to: " + volume );

        // check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;

        // if there is a music being played, change its volume
        if( musicBeingPlayed != null ) {
            musicBeingPlayed.getMusicResource().setVolume( volume );
        }
    }

    /**
     * Enables or disabled the music.
     */
    public void setEnabled(
        boolean enabled )
    {
        this.enabled = enabled;

        // if the music is being deactivated, stop any music being played
        if( ! enabled ) {
            stop();
        }
    }

    /**
     * Disposes the music manager.
     */
    public void dispose()
    {
        Gdx.app.log( OthelloGame.LOG, "Disposing music manager" );
        stop();
    }
}
