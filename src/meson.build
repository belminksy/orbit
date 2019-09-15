
src = [
	'orbit/app/conf/Conf.java',
	'orbit/app/conf/DynaConf.java',
	'orbit/app/conf/KeyConf.java',
	'orbit/app/conf/ResConf.java',
	'orbit/app/controllers/CameraController.java',
	'orbit/app/object/Planet.java',
	'orbit/app/object/Point.java',
	'orbit/app/object/Satellite.java',
	'orbit/app/scene/MasterScene.java',
	'orbit/app/scene/Scene1.java',
	'orbit/app/scene/Scene2.java',
	'orbit/app/scene/Scene3.java',
	'orbit/engine/controllers/GamepadController.java',
	'orbit/engine/controllers/MovableAndRotableController.java',
	'orbit/engine/controllers/MovableController.java',
	'orbit/engine/controllers/RotableController.java',
	'orbit/engine/group/Movable.java',
	'orbit/engine/group/Rotable.java',
	'orbit/engine/render/group/Object3D.java',
	'orbit/engine/render/group/Scene.java',
	'orbit/engine/render/manager/SceneManager.java',
	'orbit/engine/render/object/Camera.java',
	'orbit/main/Main.java',
	'orbit/main/Orbit.java',
	'orbit/utils/in/Gamepad.java',
	'orbit/utils/in/Keyboard.java',
	'orbit/utils/in/Resource.java',
	'orbit/utils/lang/Color.java',
	'orbit/utils/lang/ConfWorker.java',
	'orbit/utils/lang/Interval.java',
	'orbit/utils/lang/Rand.java',
	'orbit/utils/lang/Size.java',
	'orbit/utils/math/Point.java',
	'orbit/utils/math/Vector3f.java',
	'orbit/utils/math/WeightedRandom.java',
	'orbit/utils/out/Console.java',
	'orbit/utils/ui/LoadingDialog.java'
]


classpath = '../lib/' + ':../lib/'.join(jars)

jar(
	meson.project_name(),
	src,
	java_args: [ '-classpath', classpath ],
	main_class: 'orbit.main.Main'
)
