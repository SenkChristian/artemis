/* Copyright (C) TeamQ - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 *
 * Written by some totally awesome people.
 */

package com.artemis;

import org.junit.Assert;
import org.junit.Test;

import com.artemis.systems.EntityProcessingSystem;

/**
 * @author senk.christian@gmail.com
 */
public class EntitySystemTest {

	private class ComponentA extends Component { }
	private class ComponentB extends Component { }
	private class ComponentC extends Component { }
	
	/**
	 * Test method for {@link com.artemis.EntitySystem#check(com.artemis.Entity)}.
	 * 
	 * @see https://code.google.com/p/artemis-framework/issues/detail?id=9
	 */
	@Test
	public void testCheckWithAllOneAspect() {
		final World world = new World();
		
		world.setSystem(new EntityProcessingSystem(Aspect.getAspectForAll(ComponentA.class).one(ComponentC.class)) {
			
			@Override
			protected void process(final Entity e) {
				Assert.fail("The entity should not be processed.");
			}
			
		});
		
		final Entity entity = world.createEntity();
		entity.addComponent(new ComponentC());
		
		entity.addToWorld();
		
		world.process();
	}

}
