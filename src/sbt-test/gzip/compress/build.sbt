lazy val root = (project in file(".")).addPlugins(SbtWeb)

// for checking that the produced pipeline mappings are correct

val expected = Set(
  "css", "css/a.css", "css/a.css.gz",
  "js", "js/a.js", "js/a.js.gz",
  "images", "images/a.png", "images/b.jpg"
)

val checkMappings = taskKey[Unit]("check the pipeline mappings")

checkMappings := {
  val mappings = WebKeys.pipeline.value
  val paths = (mappings map (_._2)).toSet
  if (paths != expected) sys.error(s"Expected $expected but pipeline paths are $paths")
}
