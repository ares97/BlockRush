package radoslaw.slowinski.ares.handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import radoslaw.slowinski.ares.entites.Player;
import radoslaw.slowinski.ares.utils.Constant;

/**
 * Created by ares on 12.08.17.
 */
public class InputHandler implements InputProcessor {
    Player player;

    public InputHandler(Player player) {
        this.player = player;
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            player.getBody().applyForceToCenter(-50, 0, true);
        }
        if (keycode == Input.Keys.D) {
            player.getBody().applyForceToCenter(50, 0, true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX <= Constant.GAME_WIDTH / 2) {
            // TODO change current neutral block color
        } else {
            player.jump();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
